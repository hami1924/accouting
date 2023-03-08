package com.vista.accouting.controller.adpter;

import com.vista.accouting.controller.dto.MessageDto;
import com.vista.accouting.controller.mapper.MessageDtoMapper;
import com.vista.accouting.dal.entity.User;
import com.vista.accouting.service.RecipientsService;
import com.vista.accouting.service.UserService;
import com.vista.accouting.service.models.Message;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageDtoAdapter {
    private final RecipientsService recipientsService;

    private final UserService userService;

    public MessageDtoAdapter(RecipientsService recipientsService, MessageDtoMapper messageDtoMapper, UserService userService) {
        this.recipientsService = recipientsService;
        this.userService = userService;
    }

    public Boolean insertMessageDto(MessageDto messageDto){

        Message message=MessageDtoMapper.INSTANCE.getDto(messageDto);

        Optional<User> user=userService.getById(messageDto.getId());

        message.setUser(user.get());

        return  recipientsService.insertRecipientsList(message);

    }
}
