package com.vista.accouting.dal.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "RESIPIENTS")
public class Recipients extends Entity {

    private User user;

    // be sorat kamel nabayad inja base modelash. bayad ye model kochak shode inja  base
    private SmsNumberAlert smsNumberAlert;

    private MessageInfo messageInfo;



}