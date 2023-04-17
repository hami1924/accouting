package com.vista.accouting.dal.entity;

import com.vista.accouting.enums.TagType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CATEGORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends Entity{
    @Indexed
    private String name;
    @Indexed
    private String lang;
    @Indexed
    private TagType tagType;
    @Indexed
    private String userId;

}
