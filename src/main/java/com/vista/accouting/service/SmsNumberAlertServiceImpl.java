package com.vista.accouting.service;

import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.dal.repo.SmsNumberAlertRepository;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;
import com.vista.accouting.exceptions.NotFoundUserException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SmsNumberAlertServiceImpl implements SmsNumberAlertService {
    private final SmsNumberAlertRepository repository;

    public SmsNumberAlertServiceImpl(SmsNumberAlertRepository repository) {

        this.repository = repository;
    }

    @Override
    public SmsNumberAlert insert(SmsNumberAlert numberAlert) {

        SmsNumberAlert smsNumberAlert=repository.findByAlertHead(numberAlert.getAlertHead());
        if (Objects.nonNull(smsNumberAlert))
            throw new NotFoundUserException();

        return repository.save(numberAlert);
    }

    @Override
    public Optional<SmsNumberAlert> get(String id) {
        return repository.findById(id);
    }

    @Override
    public SmsNumberAlert getByAlertNumber(String alertNumber) {
        return repository.findByAlertHead(alertNumber);
    }

    @Override
    public List<SmsNumberAlert> list(CountryEnums countryEnums, BanksEnum banksEnum) {

        return repository.findByBanksOrCountry(banksEnum,countryEnums);
    }
}
