package com.vista.accouting.service;

import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.dal.repo.SmsNumberAlertRepository;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BankServiceImpl implements BankService{
    private final SmsNumberAlertRepository repository;

    public BankServiceImpl(SmsNumberAlertRepository repository) {
        this.repository = repository;
    }

    @Override
    public SmsNumberAlert insert(SmsNumberAlert numberAlert) {
        return repository.save(numberAlert);
    }

    @Override
    public Optional<SmsNumberAlert> get(String id) {
        return repository.findById(id);
    }

    @Override
    public List<SmsNumberAlert> list(CountryEnums countryEnums, BanksEnum banksEnum) {
        return null;
    }
}