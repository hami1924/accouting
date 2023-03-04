package com.vista.accouting.service.Provider;


import com.vista.accouting.enums.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MuscatBankServiceImpl implements BankService{

    private final String PATTERN_DATE="([0-9]+(/[0-9]+)+)";
    private final String CARD_NUMBER ="X+";
    private final String VALUE_PATTERN="[0-9]*\\.[0-9]+";


    private final String beforPattern="^.*?(?=your a/c)";


    @Override
    public boolean getMessage(String originalMessage,String smsAlert) {
        MuscatBankModel muscatBankModel=new MuscatBankModel();

        muscatBankModel.setCardNumber(getPattern(originalMessage,CARD_NUMBER));
        muscatBankModel.setOperationDate(getPattern(originalMessage,PATTERN_DATE));
        muscatBankModel.setSmsNumberAlert(smsAlert);
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(originalMessage);
//        Pattern patternCartNumber = Pattern.compile(CARD_NUMBER);
//        if (matcher.find())

      if (originalMessage.contains("credited")){
          muscatBankModel.setMessageType(MessageType.CREDIT);
          muscatBankModel.setCurrentValue(getPattern(originalMessage.substring(originalMessage.indexOf("your a/c")),VALUE_PATTERN));
          String partToBalance=getPattern(originalMessage,beforPattern);
          muscatBankModel.setBalanceValue(getPattern(partToBalance,VALUE_PATTERN));

      }else if (originalMessage.contains("withdrawn")){

      }else if (originalMessage.contains("Card of a/c")){

      }
        return false;
    }

    private String getPattern(String originalMessage,String pattern) {
        Pattern patternDate = Pattern.compile(pattern);
        Matcher matcher = patternDate.matcher(originalMessage);
        if (matcher.find()){
            Optional<String> match= Optional.ofNullable(matcher.group());
            if (match.isPresent())
                return match.get();
        }
        return "";
    }



    @Data
    class MuscatBankModel{
        private String cardNumber;
        private String operationDate;
        private String currentValue;
        private String BalanceValue;
        private String place;
        private MessageType messageType;
        private String smsNumberAlert;
    }
}
