package com.vista.accouting.service.Provider;

import com.vista.accouting.dal.entity.MessageInfo;
import com.vista.accouting.enums.BanksEnum;
import org.springframework.stereotype.Component;

@Component
public class PasargardServiceImpl implements BankService{


    @Override
    public boolean support(BanksEnum banksEnum) {
        return false;
    }

    @Override
    public MessageInfo getMessage(String originalMessage, String smsAlert) {
        return null;
    }
}
