package com.vista.accouting.service.patterns;

import com.vista.accouting.aspect.GlobalObject;
import com.vista.accouting.aspect.SynchronizedGlobalObjectHelper;
import com.vista.accouting.exceptions.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatternRecognizedBuilder {

    private final PatternRecognizedDecider patternRecognizedDecider;

    private final SynchronizedGlobalObjectHelper synchronizedGlobalObjectHelper;


    public PatternRecognizedBuilder(PatternRecognizedDecider patternRecognizedDecider, SynchronizedGlobalObjectHelper synchronizedGlobalObjectHelper) {
        this.patternRecognizedDecider = patternRecognizedDecider;
        this.synchronizedGlobalObjectHelper = synchronizedGlobalObjectHelper;
    }


    public PatternRecognized getInstance(String bin) throws ServiceException {
        GlobalObject tosanBoomRegistry = SynchronizedGlobalObjectHelper.getGlobalObject();
        BankDto bankDto = bankRepos.getBankByBankBin(bin);
        tosanBoomRegistry.setBank(bankDto);
        return getInstance();
    }

    public PatternRecognized getInstance() throws ServiceException {
        GlobalObject tosanBoomRegistry = synchronizedGlobalObjectHelper.getTbRegistry();
        if (tosanBoomRegistry.getBank() == null) {
            return boomProvider;
    }

}
