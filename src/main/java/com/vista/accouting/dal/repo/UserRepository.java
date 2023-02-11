package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByImei(String imei);

    Optional<User> findById(ObjectId id);

    List<User> findByMobileOrImei(String mobile, String imei);

}
