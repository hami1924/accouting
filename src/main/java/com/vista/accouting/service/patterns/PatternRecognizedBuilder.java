package com.vista.accouting.service.patterns;

import com.vista.accouting.aspect.globalObject.GlobalObject;
import com.vista.accouting.aspect.globalObject.SynchronizedGlobalObjectHelper;
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
        GlobalObject globalObject = SynchronizedGlobalObjectHelper.getGlobalObject();
        return getInstance();
    }

    public PatternRecognized getInstance() throws ServiceException {
        GlobalObject globalObject = synchronizedGlobalObjectHelper.getGlobalObject();

            Optional<PatternRecognized> patternRecognized = patternRecognizedDecider.decide(globalObject.getBanksEnum());
            return patternRecognized.orElseGet(() -> null);
    }

}
