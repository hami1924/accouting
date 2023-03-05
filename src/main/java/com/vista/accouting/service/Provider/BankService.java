package com.vista.accouting.service.Provider;


import com.vista.accouting.dal.entity.MessageInfo;
import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;

import java.util.List;
import java.util.Optional;

public interface BankService {


    MessageInfo getMessage(String originalMessage, String smsAlert);



}
