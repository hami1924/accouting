package com.vista.accouting.controller.mapper;

import com.vista.accouting.controller.dto.TagDto;
import com.vista.accouting.dal.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TagDtoMapper {

    TagDtoMapper INSTANCE= Mappers.getMapper(TagDtoMapper.class);

    TagEntity getEntity(TagDto tagDto);
}
