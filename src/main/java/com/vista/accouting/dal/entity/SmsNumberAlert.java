package com.vista.accouting.dal.entity;

import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "NUMBER_ALERT")
public class SmsNumberAlert extends Entity{

    private String alertHead;

    private BanksEnum banks;

    private CountryEnums country;

}
