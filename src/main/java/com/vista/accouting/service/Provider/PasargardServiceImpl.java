package com.vista.accouting.service.Provider;

import com.vista.accouting.dal.entity.MessageInfo;
import org.springframework.stereotype.Component;

@Component
public class PasargardServiceImpl implements BankService{


    @Override
    public MessageInfo getMessage(String originalMessage, String smsAlert) {
        return null;
    }
}
