package com.vista.accouting.dal.entity;

import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;
import lombok.Data;

@Data
public class SmsNumberAlert extends Entity{

    private String alertHead;

    private BanksEnum banks;

    private CountryEnums countryEnums;

}
