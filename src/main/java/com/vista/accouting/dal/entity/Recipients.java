package com.vista.accouting.dal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "RESIPIENTS")
public class Recipients {

    @Id
    private String uniqueId;

    private User user;

}