package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.TagEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<TagEntity,String > {

    List<TagEntity> findByUserIdAndTagType(String userId, String tagType);

    Optional<TagEntity> findByName(String name);
}
