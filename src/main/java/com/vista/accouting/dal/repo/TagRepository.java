package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.enums.TagType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends MongoRepository<TagEntity,String > {

    List<TagEntity> findByUserIdOrAndTagType(String userId, String tagType);
}
