package com.vista.accouting.dal.repo;

import com.vista.accouting.dal.entity.TagEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<TagEntity,String > {

    List<TagEntity> findByUserIdOrTagType(String userId, String tagType);

    Optional<TagEntity> findByName(String name);

    Optional<TagEntity> findById(ObjectId id);

    List<TagEntity>  findByCategoryIdAndTagType(String categoryId,String tagType);
}
