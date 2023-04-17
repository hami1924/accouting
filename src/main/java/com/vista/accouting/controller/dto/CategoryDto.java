package com.vista.accouting.controller.dto;

import com.sun.istack.NotNull;
import com.vista.accouting.enums.TagType;
import lombok.Data;
import net.shibboleth.utilities.java.support.annotation.constraint.NotEmpty;

@Data
public class CategoryDto {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String lang;
    @NotNull
    @NotEmpty
    private TagType tagType;
    private String userId;
}
