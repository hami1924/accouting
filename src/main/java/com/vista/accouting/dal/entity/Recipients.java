package com.vista.accouting.dal.entity;

import com.vista.accouting.enums.MessageType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "RESIPIENTS")
public class Recipients extends Entity {

    private LocalDate date;

    private String messageHash;

    private MessageType messageType;

    private Float amount;

    private String metaData;

    private String originalDataBase64;

    private String cardNumber;
    private String operationDate;
    private Float BalanceValue;
    private String place;
    private String currency;


    private User user;

    // be sorat kamel nabayad inja base modelash. bayad ye model kochak shode inja  base
    private SmsNumberAlert smsNumberAlert;

//    private MessageInfo messageInfo;


}