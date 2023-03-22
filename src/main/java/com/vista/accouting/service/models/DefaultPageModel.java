package com.vista.accouting.service.models;



import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DefaultPageModel {

    LocalDate from;
    LocalDate to;
     List<DetailPay> list;
    @Data
    public class DetailPay{

        private String currency;
        private Withdraw withdraw;
        private Deposit cerdit;
    }
    @Data
    public class Withdraw{
        Float amount;
    }
    @Data
    public class Deposit{
        Float amount;
    }
}


