package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.Category;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.service.models.MessageQuery;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecipientsRepository extends MongoRepository<Recipients,String> , CustomRecipientsRepository {

    Optional<Recipients> findById(ObjectId id);

}
