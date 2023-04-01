package com.vista.accouting.dal.entity;

import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "NUMBER_ALERT")
public class SmsNumberAlert extends Entity{
    @Indexed
    private String alertHead;
    @Indexed
    private BanksEnum banks;
    @Indexed
    private CountryEnums country;

}
