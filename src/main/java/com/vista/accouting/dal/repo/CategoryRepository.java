package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

    List<Category> findByUserIdAndTagType(String userid,String tagTape);

    Optional<Category> findById(ObjectId id);
}
