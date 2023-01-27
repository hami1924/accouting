package com.vista.accouting.service;

import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.dal.repo.SmsNumberAlertRepository;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;

import java.util.List;
import java.util.Optional;

public class SmsNumberAlertServiceImpl implements SmsNumberAlertService {
    private final SmsNumberAlertRepository repository;

    public SmsNumberAlertServiceImpl(SmsNumberAlertRepository repository) {
        this.repository = repository;
    }

    @Override
    public com.vista.accouting.dal.entity.SmsNumberAlert insert(com.vista.accouting.dal.entity.SmsNumberAlert numberAlert) {
        return repository.save(numberAlert);
    }

    @Override
    public Optional<com.vista.accouting.dal.entity.SmsNumberAlert> get(String id) {
        return repository.findById(id);
    }

    @Override
    public List<SmsNumberAlert> list(CountryEnums countryEnums, BanksEnum banksEnum) {
        return null;
    }
}
