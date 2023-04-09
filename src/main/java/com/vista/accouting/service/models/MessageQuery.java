package com.vista.accouting.service.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mongodb.lang.Nullable;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CurrencyType;
import com.vista.accouting.enums.MessageType;
import com.vista.accouting.util.log.JsonDateDeserializer;
import com.vista.accouting.util.log.JsonDateSerializer;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageQuery extends PageCustomModel{
    @JsonSerialize(using= JsonDateSerializer.class)
    @JsonDeserialize(using= JsonDateDeserializer.class)
    private LocalDate from;
    @JsonSerialize(using=JsonDateSerializer.class)
    @JsonDeserialize(using=JsonDateDeserializer.class)
    private LocalDate to;

    @Nullable
    private BanksEnum banksEnum;
    @Nullable
    private MessageType messageType;

    @NonNull
    private String userId;

    private CurrencyType currencyType ;

    private String tag;
}
