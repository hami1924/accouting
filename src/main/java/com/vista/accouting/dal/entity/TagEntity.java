package com.vista.accouting.dal.entity;

import com.vista.accouting.enums.TagType;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TAG")
@Data
public class TagEntity extends Entity {
    @Indexed
    private String name;
    @Indexed
    private String lang;
    @Indexed
    private TagType tagType;
    @Indexed
    private String userId;

}
