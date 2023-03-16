package com.vista.accouting.service;

import com.vista.accouting.aspect.globalObject.GlobalObject;
import com.vista.accouting.aspect.globalObject.SynchronizedGlobalObjectHelper;
import com.vista.accouting.dal.entity.MessageInfo;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.dal.repo.RecipientsRepository;
import com.vista.accouting.exceptions.ServiceException;
import com.vista.accouting.service.models.DefaultPageModel;
import com.vista.accouting.service.models.Message;
import com.vista.accouting.service.models.MessageQuery;
import com.vista.accouting.service.models.MessageView;
import com.vista.accouting.service.patterns.PatternRecognizedBuilder;
import com.vista.accouting.service.patterns.PatternRecognizedDecider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RecipientsServiceImpl implements RecipientsService{

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


        SmsNumberAlert smsNumberAlert=alertService
                .getByAlertNumber(messageModel.getNumberAlert());
        globalObject.setBanksEnum(smsNumberAlert.getBanks());

        for (String message:messageModel.getData()){
            Recipients recipients=new Recipients();
            try {
                 recipients=builder.getInstance(smsNumberAlert.getBanks()).getMessage(message,messageModel.getNumberAlert(),recipients);
//                recipients.setMessageInfo(messageInfo);
                recipients.setSmsNumberAlert(smsNumberAlert);
                recipients.setUser(messageModel.getUser());
            } catch (ServiceException e) {
                throw new RuntimeException("message not to able procsess");
            }
            repository.save(recipients);

        }
        return true;
    }

    @Override
    public Page<Recipients> messageList(MessageQuery messageQuery) {
        return repository.findByQueryCustom(messageQuery, PageRequest.of(messageQuery.getPage(),messageQuery.getSize()));
    }

    @Override
    public DefaultPageModel firstPage(MessageQuery messageQuery) {

        return null;
    }
}
