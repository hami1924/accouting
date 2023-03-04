package com.vista.accouting.service.Provider;

import org.springframework.stereotype.Component;

@Component
public class PasargardServiceImpl implements BankService{


    @Override
    public boolean getMessage(String originalMessage, String smsAlert) {
        return false;
    }
}
