package com.vista.accouting.service;


import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;

import java.util.List;
import java.util.Optional;

public interface BankService {

     SmsNumberAlert insert(SmsNumberAlert numberAlert);

    Optional<SmsNumberAlert> get(String id);

    List<SmsNumberAlert> list(CountryEnums countryEnums, BanksEnum banksEnum);


}
