package com.vista.accouting.controller.mapper;

import com.vista.accouting.controller.dto.MessageDto;
import com.vista.accouting.service.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MessageDtoMapper {
    MessageDtoMapper INSTANCE= Mappers.getMapper(MessageDtoMapper.class);

    @Mapping(target = "data", ignore = true)
    Message getDto(MessageDto messageDto);
}
