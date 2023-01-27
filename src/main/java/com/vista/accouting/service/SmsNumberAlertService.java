package com.vista.accouting.service;

import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;

import java.util.List;
import java.util.Optional;

public interface SmsNumberAlertService {

    com.vista.accouting.dal.entity.SmsNumberAlert insert(com.vista.accouting.dal.entity.SmsNumberAlert numberAlert);

    Optional<com.vista.accouting.dal.entity.SmsNumberAlert> get(String id);

    List<com.vista.accouting.dal.entity.SmsNumberAlert> list(CountryEnums countryEnums, BanksEnum banksEnum);
}
