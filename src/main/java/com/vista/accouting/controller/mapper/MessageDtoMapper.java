package com.vista.accouting.controller.mapper;

import com.vista.accouting.controller.dto.MessageDto;
import com.vista.accouting.service.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageDtoMapper {
    MessageDtoMapper INSTANCE= Mappers.getMapper(MessageDtoMapper.class);


    Message getDto(MessageDto messageDto);
}
