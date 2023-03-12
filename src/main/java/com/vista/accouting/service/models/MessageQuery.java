package com.vista.accouting.service.models;

import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageQuery {

    private LocalDate from;

    private LocalDate to;

    private BanksEnum banksEnum;

    private MessageType messageType;
}
