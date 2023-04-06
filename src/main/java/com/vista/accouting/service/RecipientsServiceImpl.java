package com.vista.accouting.service;

import com.vista.accouting.aspect.globalObject.GlobalObject;
import com.vista.accouting.aspect.globalObject.SynchronizedGlobalObjectHelper;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.dal.repo.RecipientsRepository;
import com.vista.accouting.enums.MessageType;
import com.vista.accouting.exceptions.ServiceException;
import com.vista.accouting.service.models.*;
import com.vista.accouting.service.patterns.PatternRecognizedBuilder;
import com.vista.accouting.service.patterns.PatternRecognizedDecider;
import com.vista.accouting.util.log.Utils;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
@Slf4j
public class RecipientsServiceImpl implements RecipientsService {

    private final PatternRecognizedDecider patternRecognizedDecider;

    private final PatternRecognizedBuilder builder;

    private final RecipientsRepository repository;

    private final SmsNumberAlertService alertService;


    public RecipientsServiceImpl(PatternRecognizedDecider patternRecognizedDecider, PatternRecognizedBuilder builder, RecipientsRepository repository, SmsNumberAlertService alertService) {
        this.patternRecognizedDecider = patternRecognizedDecider;
        this.builder = builder;
        this.repository = repository;
        this.alertService = alertService;
    }


    @Override
    public boolean insertRecipientsList(Message messageModel) {
        GlobalObject globalObject = SynchronizedGlobalObjectHelper.getGlobalObject();


        SmsNumberAlert smsNumberAlert = alertService
                .getByAlertNumber(messageModel.getNumberAlert());
        globalObject.setBanksEnum(smsNumberAlert.getBanks());

        for (String message : messageModel.getData()) {
            String hashMessage=Utils.HashSha128(message);
            List<Recipients> list=repository.findUserIdAndMessageHash(new ObjectId(messageModel.getUser().getId()),hashMessage);
            if (list.size()>0)
                continue;
            Recipients recipients = new Recipients();
            try {
                recipients = builder.getInstance(smsNumberAlert.getBanks()).getMessage(message, messageModel.getNumberAlert(), recipients);
//                recipients.setMessageInfo(messageInfo);
                recipients.setSmsNumberAlert(smsNumberAlert);
                recipients.setUser(messageModel.getUser());
                recipients.setMessageHash(hashMessage);
            } catch (Exception e) {
                log.error("message not to able procsess user: " +messageModel.getUser().getId() + " hashMessage :"+ hashMessage);
//                throw new RuntimeException("message not to able procsess");
                continue;
            }
            repository.save(recipients);

        }
        return true;
    }

    @Override
    public Page<Recipients> messageList(MessageQuery messageQuery) {
        return repository.findByQueryCustom(messageQuery, PageRequest.of(messageQuery.getPage(), messageQuery.getSize()));
    }

    @Override
    public DefaultPageModel firstPage(MessageQuery messageQuery) {

        DefaultPageModel defaultPageModel = new DefaultPageModel();
        defaultPageModel.setFrom(messageQuery.getFrom());
        defaultPageModel.setTo(messageQuery.getTo());
        defaultPageModel.setList(new ArrayList<>());

        DefaultPageModel.DetailPay detailPayWithdraw=getAmountWithMessageType(messageQuery,MessageType.WITHDRAW,defaultPageModel);
        DefaultPageModel.DetailPay detailPayDeposit=getAmountWithMessageType(messageQuery,MessageType.CREDIT,defaultPageModel);

        DefaultPageModel.DetailPay detailPay=defaultPageModel.new DetailPay();

        detailPay.setCurrency(Objects.nonNull(detailPayWithdraw.getCurrency())?detailPayWithdraw.getCurrency():"RIAL-OMAN");
        detailPay.setWithdraw(detailPayWithdraw.getWithdraw());
        detailPay.setCerdit(detailPayDeposit.getCerdit());

        defaultPageModel.getList().add(detailPay);
        return defaultPageModel;
    }

    private DefaultPageModel.DetailPay getAmountWithMessageType(MessageQuery messageQuery ,MessageType messageType,DefaultPageModel defaultPageModel){
        MessageQuery messageQueryWithdraw = new MessageQuery();
        messageQueryWithdraw.setFrom(messageQuery.getFrom());
        messageQueryWithdraw.setTo(messageQuery.getTo());
        messageQueryWithdraw.setMessageType(messageType);
        messageQueryWithdraw.setUserId(messageQuery.getUserId());
        messageQueryWithdraw.setBanksEnum(messageQuery.getBanksEnum());
        messageQueryWithdraw.setCurrencyType(messageQuery.getCurrencyType());
        List<Recipients> recipientsList = repository.findByQueryCustomWithoutPageable(messageQueryWithdraw);
        Float amountSum = 0F;
        for (Recipients recipients : recipientsList) {
            if (Objects.nonNull(recipients.getAmount()))
                amountSum = amountSum + recipients.getAmount();
        }
//        DefaultPageModel defaultPageModelWithDraw = new DefaultPageModel();
        DefaultPageModel.DetailPay detailPay = defaultPageModel.new DetailPay();
        if(recipientsList.size()>0)
        detailPay.setCurrency(recipientsList.get(0).getCurrency());

        if (messageType.equals(MessageType.WITHDRAW)) {
            detailPay.setWithdraw(setAmountWitWithdraw(amountSum, defaultPageModel));
        }else if(messageType.equals(MessageType.CREDIT)){
        detailPay.setCerdit(setAmountWitDeposit(amountSum,defaultPageModel));

        }
        return detailPay;
    }

    private DefaultPageModel.Deposit setAmountWitDeposit(Float amountSum, DefaultPageModel defaultPageModel) {
        DefaultPageModel.Deposit deposit = defaultPageModel.new Deposit();
        deposit.setAmount(amountSum);
        return deposit;
    }

    private DefaultPageModel.Withdraw setAmountWitWithdraw(Float amountSum,DefaultPageModel defaultPageModel) {
        DefaultPageModel.Withdraw withdraw = defaultPageModel.new Withdraw();
        withdraw.setAmount(amountSum);
        return withdraw;
    }
}
