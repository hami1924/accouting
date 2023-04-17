package com.vista.accouting.controller.mapper;

import com.vista.accouting.controller.dto.CategoryDto;
import com.vista.accouting.dal.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryDtoMapper {
    CategoryDtoMapper INSTANCE= Mappers.getMapper(CategoryDtoMapper.class);

    Category getEntity(CategoryDto categoryDto);
}
