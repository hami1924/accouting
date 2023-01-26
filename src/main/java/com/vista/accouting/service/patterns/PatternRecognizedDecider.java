package com.vista.accouting.service.patterns;

import com.vista.accouting.enums.BanksEnum;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class PatternRecognizedDecider {
    private final List<PatternRecognized> serviceProviders;


    public PatternRecognizedDecider(List<PatternRecognized> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    public Optional<PatternRecognized> decide(BanksEnum banksEnum) {
        Iterator<PatternRecognized> serviceProviderIterator = serviceProviders.iterator();
        while (serviceProviderIterator.hasNext()) {
            PatternRecognized serviceProvider = serviceProviderIterator.next();
            if (serviceProvider.support(banksEnum)) {
                return Optional.of(serviceProvider);
            }
        }
        return Optional.empty();
    }

}
