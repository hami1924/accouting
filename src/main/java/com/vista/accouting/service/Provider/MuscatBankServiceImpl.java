package com.vista.accouting.service.Provider;


import com.vista.accouting.enums.MessageType;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MuscatBankServiceImpl implements BankService{

    private final String patternDate="([0-9]+(/[0-9]+)+)";
    private final String CARD_NUMBER ="X+";
    private final String value="[0-9]*\\.[0-9]+";


    @Override
    public boolean getMessage(String originalMessage) {
        MuscatBankModel muscatBankModel=new MuscatBankModel();
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(originalMessage);
//        Pattern patternCartNumber = Pattern.compile(CARD_NUMBER);
//        if (matcher.find())

      if (originalMessage.contains("credited")){


      }else if (originalMessage.contains("withdrawn")){

      }else if (originalMessage.contains("Card of a/c")){

      }
        return false;
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
