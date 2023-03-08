package com.vista.accouting.service.patterns;

import com.vista.accouting.aspect.globalObject.GlobalObject;
import com.vista.accouting.aspect.globalObject.SynchronizedGlobalObjectHelper;
import com.vista.accouting.exceptions.ServiceException;
import com.vista.accouting.service.Provider.BankService;
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


    public BankService getInstance(String bin) throws ServiceException {
        GlobalObject globalObject = SynchronizedGlobalObjectHelper.getGlobalObject();
        return getInstance();
    }

    public BankService getInstance() throws ServiceException {
        GlobalObject globalObject = synchronizedGlobalObjectHelper.getGlobalObject();

            Optional<BankService> patternRecognized = patternRecognizedDecider.decide(globalObject.getBanksEnum());
            return patternRecognized.orElseGet(() -> null);
    }

}
