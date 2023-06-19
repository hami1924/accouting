package com.vista.accouting.service.Provider;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CurrencyType;
import com.vista.accouting.enums.MessageType;
import com.vista.accouting.exceptions.NotFoundUserException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MuscatBankServiceImpl implements BankService , MetaDataInfo{

    private final String PATTERN_DATE = "([0-9]+(/[0-9]+)+)";
    private final String CARD_NUMBER = "[0-9][0-9][0-9][0-9]X+[0-9][0-9][0-9][0-9]";
    private final String VALUE_PATTERN = "[0-9]*\\.[0-9]+";


    private final String beforPatternCredit = "^.*?(?=your a/c)";
    private final String beforePatternWithdrawn = "^.*?(?=at)";
    private final String beforePatternWithdrawnATM = "^.*?(?=through)";

    private final String CURRENCY="OMR";


    @Override
    public boolean support(BanksEnum banksEnum) {
        return BanksEnum.MUSCAT_BANK.equals(banksEnum);
    }

    @Override
    public Recipients getMessage(String originalMessage, String smsAlert, Recipients muscatBankModel) {

        muscatBankModel.setCardNumber(getPattern(originalMessage, CARD_NUMBER));
        String dateOperation = dateRecognize(originalMessage);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        muscatBankModel.setOperationDate(dateOperation);
        LocalDate dateTime = LocalDate.parse(dateOperation, formatter);
        muscatBankModel.setDate(dateTime);
        muscatBankModel.setCurrency(CurrencyType.OMR.toString());

        if (originalMessage.contains("credited")) {
            muscatBankModel.setMessageType(MessageType.CREDIT);
            muscatBankModel.setBalanceValue(Float.parseFloat(getPattern(originalMessage
                    .substring(originalMessage.indexOf("your a/c")), VALUE_PATTERN)));
            String partToBalance = getPattern(originalMessage, beforPatternCredit);
            muscatBankModel.setAmount(Float.parseFloat(getPattern(partToBalance, VALUE_PATTERN)));
            muscatBankModel.setTag(getDefaultCreditTagObject(muscatBankModel.getUser().getId(),null));
            muscatBankModel.setCategory(getDefaultCreditCategoryObject(muscatBankModel.getUser().getId()));

        } else if (originalMessage.contains("withdrawn")) {

            muscatBankModel.setMessageType(MessageType.WITHDRAW);
            muscatBankModel.setBalanceValue(Float.parseFloat(getPattern(originalMessage
                    .substring(originalMessage.indexOf("through")), VALUE_PATTERN)));
            String partToBalance = getPattern(originalMessage, beforePatternWithdrawnATM);
            muscatBankModel.setAmount(Float.parseFloat(getPattern(partToBalance, VALUE_PATTERN)));
            muscatBankModel.setTag(getDefaultCashTagObject(muscatBankModel.getUser().getId(),null));
            muscatBankModel.setCategory(getDefaultCashCategoryObject(muscatBankModel.getUser().getId()));

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

    private String dateRecognize(String message){
        String text= (StringUtils.substringBetween(message,"on",".")).strip();
        if (Objects.nonNull(text) && text.contains("/")){
            String dateOperation = getPattern(message, PATTERN_DATE);
            return dateOperation;
        }else {
            if(text.contains("JAN")){
                return createDateWithFormat(text,"JAN","01");
            } else if(text.contains("FEB")){
                return createDateWithFormat(text,"FEB","02");
            }else if(text.contains("MAR")){
                return createDateWithFormat(text,"MAR","03");
            }else if(text.contains("APR")){
                return createDateWithFormat(text,"APR","04");
            }else if(text.contains("MAY")){
                return createDateWithFormat(text,"MAY","05");
            }else if(text.contains("JUN")){
                return createDateWithFormat(text,"JUN","06");
            }else if(text.contains("JUL")){
                return createDateWithFormat(text,"JUL","07");
            }else if(text.contains("AUG")){
                return createDateWithFormat(text,"AUG","08");
            }else if(text.contains("SEP")){
                return createDateWithFormat(text,"SEP","09");
            }else if(text.contains("OCT")){
                return createDateWithFormat(text,"OCT","10");
            }else if(text.contains("NOV")){
                return createDateWithFormat(text,"NOV","11");
            }else if(text.contains("DEC")){
                return createDateWithFormat(text,"DEC","12");
            }
        }
        return null;
    }
    //create best format for us.
    private String createDateWithFormat(String test,String month,String number){
        String[] testSplit=StringUtils.split(test,month);
        String firstPart=testSplit[0].strip();
        String[] textPartSplitTwo=StringUtils.split(testSplit[1]);
        String secondPart=textPartSplitTwo[0].strip();
        String result=firstPart+"/"+number+"/"+secondPart;

        return result;
    }


}
