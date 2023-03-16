package com.vista.accouting.service.models;


import com.vista.accouting.enums.MessageType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DefaultPageModel {

    LocalDate from;
    LocalDate to;
     List<DetailPay> list;

}

@Data
class DetailPay{

    MessageType type;
    Long amount;
}
