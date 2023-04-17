package com.vista.accouting.service;

import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.service.models.TagDefaultPageModel;

import java.util.List;

public interface TagService {
    TagEntity insert(TagEntity tagEntity);

    TagEntity findById(String id);

    TagEntity editById(String id,TagEntity tagEntity);

    TagEntity findByName(String name);

    List<TagEntity> list(String userId);

    List<TagEntity> listAdmin();

    void deleteById(String id);

    TagEntity findTagInContent(String UserId,String content);


}
