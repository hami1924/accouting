package com.vista.accouting.aspect.globalObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vista.accouting.common.ErrorResponse;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;
import lombok.Data;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GlobalObject {

    private Set<ErrorResponse.Error> errorResponses;

    private String userId;

    private BanksEnum banksEnum;

    private CountryEnums countryEnums;

}
