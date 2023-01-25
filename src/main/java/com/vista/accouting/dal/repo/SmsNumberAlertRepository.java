package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.SmsNumberAlert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsNumberAlertRepository extends MongoRepository<SmsNumberAlert,String> {
}
