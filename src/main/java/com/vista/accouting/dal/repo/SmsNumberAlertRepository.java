package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsNumberAlertRepository extends MongoRepository<SmsNumberAlert,String> {

    List<SmsNumberAlert> findByBanksOrCountry(BanksEnum banksEnum, CountryEnums countryEnums);


}
