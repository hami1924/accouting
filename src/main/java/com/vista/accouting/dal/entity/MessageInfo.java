package com.vista.accouting.dal.entity;

import com.vista.accouting.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageInfo {

    private LocalDate date;

    private String messageHash;

    private MessageType messageType;

    private Float amount;

    private String metaData;

    private String originalDataBase64;

}
