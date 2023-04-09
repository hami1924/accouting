package com.vista.accouting.dal.entity;

import com.vista.accouting.enums.MessageType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "RESIPIENTS")
public class Recipients extends Entity {

    @Indexed
    private LocalDate date;

    @Indexed
    private String messageHash;

    @Indexed
    private MessageType messageType;

    @Indexed
    private Float amount;

    private String metaData;

    @Indexed
    private String originalDataBase64;

    @Indexed
    private String cardNumber;
    @Indexed
    private String operationDate;
    @Indexed
    private Float BalanceValue;
    @Indexed
    private String place;
    @Indexed
    private String currency;



    private User user;

    // be sorat kamel nabayad inja base modelash. bayad ye model kochak shode inja  base
    private SmsNumberAlert smsNumberAlert;

    private TagEntity tag;

//    private MessageInfo messageInfo;


}