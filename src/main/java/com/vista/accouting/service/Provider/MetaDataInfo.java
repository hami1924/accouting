package com.vista.accouting.service.Provider;

import com.vista.accouting.dal.entity.Category;
import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.enums.TagType;

public interface MetaDataInfo {

    default TagEntity getDefaultCashTagObject(String userId, String groupId){
        return new TagEntity("CASH","en",
                TagType.GENERAL,userId,groupId);
    }

    default TagEntity getDefaultUnknownTagObject(String userId, String groupId){
        return new TagEntity("UNKNOWN", "en",
                TagType.GENERAL, userId,groupId);
    }
    default TagEntity getDefaultCreditTagObject(String userId, String groupId){
        return new TagEntity("CREDIT", "en",
                TagType.GENERAL, userId,groupId);
    }

    default Category getDefaultCashCategoryObject(String userId){
        return new Category("CASH", "en",
                TagType.GENERAL, userId);
    }

    default Category getDefaultUnknownCategoryObject(String userId){
        return new Category("UNKNOWN", "en",
                TagType.GENERAL, userId);
    }

    default Category getDefaultCreditCategoryObject(String userId){
        return new Category("CREDIT", "en",
                TagType.GENERAL, userId);
    }
}
