package com.vista.accouting.service.patterns;

import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.service.Provider.BankService;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class PatternRecognizedDecider {
    private final List<BankService> serviceProviders;


    public PatternRecognizedDecider(List<BankService> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    public Optional<BankService> decide(BanksEnum banksEnum) {
        Iterator<BankService> serviceProviderIterator = serviceProviders.iterator();
        while (serviceProviderIterator.hasNext()) {
            BankService serviceProvider = serviceProviderIterator.next();
            if (serviceProvider.support(banksEnum)) {
                return Optional.of(serviceProvider);
            }
        }
        return Optional.empty();
    }

}
