package com.vista.accouting.service.Provider;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vista.accouting.dal.entity.MessageInfo;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CurrencyType;
import com.vista.accouting.enums.MessageType;
import com.vista.accouting.exceptions.NotFoundUserException;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MuscatBankServiceImpl implements BankService {

    private final String PATTERN_DATE = "([0-9]+(/[0-9]+)+)";
    private final String CARD_NUMBER = "[0-9][0-9][0-9][0-9]X+[0-9][0-9][0-9][0-9]";
    private final String VALUE_PATTERN = "[0-9]*\\.[0-9]+";


    private final String beforPatternCredit = "^.*?(?=your a/c)";
    private final String beforePatternWithdrawn = "^.*?(?=at)";
    private final String beforePatternWithdrawnATM = "^.*?(?=through)";

    private final String CURRENCY="RIAL-OMAN";


    @Override
    public boolean support(BanksEnum banksEnum) {
        return BanksEnum.MUSCAT_BANK.equals(banksEnum);
    }

    @Override
    public Recipients getMessage(String originalMessage, String smsAlert, Recipients muscatBankModel) {

        muscatBankModel.setCardNumber(getPattern(originalMessage, CARD_NUMBER));
        String dateOperation = getPattern(originalMessage, PATTERN_DATE);
        muscatBankModel.setOperationDate(dateOperation);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(dateOperation, formatter);
        muscatBankModel.setDate(dateTime);
        muscatBankModel.setCurrency(CurrencyType.RIAL_OMAN.toString());

        if (originalMessage.contains("credited")) {
            muscatBankModel.setMessageType(MessageType.CREDIT);
            muscatBankModel.setBalanceValue(Float.parseFloat(getPattern(originalMessage
                    .substring(originalMessage.indexOf("your a/c")), VALUE_PATTERN)));
            String partToBalance = getPattern(originalMessage, beforPatternCredit);
            muscatBankModel.setAmount(Float.parseFloat(getPattern(partToBalance, VALUE_PATTERN)));

        } else if (originalMessage.contains("withdrawn")) {

            muscatBankModel.setMessageType(MessageType.WITHDRAW);
            muscatBankModel.setBalanceValue(Float.parseFloat(getPattern(originalMessage
                    .substring(originalMessage.indexOf("through")), VALUE_PATTERN)));
            String partToBalance = getPattern(originalMessage, beforePatternWithdrawnATM);
            muscatBankModel.setAmount(Float.parseFloat(getPattern(partToBalance, VALUE_PATTERN)));

        } else if (originalMessage.contains("Card of a/c")) {
            muscatBankModel.setMessageType(MessageType.WITHDRAW);
            muscatBankModel.setBalanceValue(Float.parseFloat(getPattern(originalMessage
                    .substring(originalMessage.indexOf("at")), VALUE_PATTERN)));
            String partToBalance = getPattern(originalMessage, beforePatternWithdrawn);
            muscatBankModel.setAmount(Float.parseFloat(getPattern(partToBalance, VALUE_PATTERN)));
        }else {
            throw new NotFoundUserException();
        }
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(muscatBankModel);
            muscatBankModel.setMetaData(result);
        } catch (JsonProcessingException e) {
            throw new InternalError();
        }
        return muscatBankModel;
    }

    private String getPattern(String originalMessage, String pattern) {
        Pattern patternDate = Pattern.compile(pattern);
        Matcher matcher = patternDate.matcher(originalMessage);
        if (matcher.find()) {
            Optional<String> match = Optional.ofNullable(matcher.group());
            if (match.isPresent())
                return match.get();
        }
        return "";
    }


}
