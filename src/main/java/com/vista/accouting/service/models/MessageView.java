package com.vista.accouting.service.models;


import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageView {
    private String id;

    private MessageType type;

    private Float amount;

    private BanksEnum banksEnum;

    private Date operationDate;


}
