package com.vista.accouting.dal.entity;

import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "NUMBER_ALERT")
public class SmsNumberAlert extends Entity{

    private String alertHead;

    private BanksEnum banks;

    private CountryEnums country;

}
