package com.vista.accouting.controller.adpter;

import com.vista.accouting.controller.dto.ContentDto;
import com.vista.accouting.controller.dto.MessageDto;
import com.vista.accouting.controller.mapper.MessageDtoMapper;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.dal.entity.User;
import com.vista.accouting.exceptions.NotFoundUserException;
import com.vista.accouting.service.RecipientsService;
import com.vista.accouting.service.UserService;
import com.vista.accouting.service.models.DefaultPageModel;
import com.vista.accouting.service.models.Message;
import com.vista.accouting.service.models.MessageQuery;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MessageDtoAdapter {
    private final RecipientsService recipientsService;

    private final UserService userService;


    public MessageDtoAdapter(RecipientsService recipientsService, UserService userService) {
        this.recipientsService = recipientsService;
        this.userService = userService;
    }

    public Boolean insertMessageDto(MessageDto messageDto){

        Message message=MessageDtoMapper.INSTANCE.getDto(messageDto);

        List<String> messageList=new ArrayList<>();
        for (ContentDto contentDto:messageDto.getData())
            messageList.add(contentDto.getContent());

        message.setData(messageList);
        Optional<User> user=userService.getById(messageDto.getId());

        if (user.isPresent())
             message.setUser(user.get());

        return  recipientsService.insertRecipientsList(message);

    }


    public Page<Recipients> listMessage(MessageQuery messageQuery) {
        Optional<User> user=userService.getById(messageQuery.getUserId());
        if (!user.isPresent())
            throw new NotFoundUserException();
        return recipientsService.messageList(messageQuery);
    }

    public DefaultPageModel defaultPage(MessageQuery messageQuery)  {
        Optional<User> user=userService.getById(messageQuery.getUserId());
        if (!user.isPresent())
            throw new NotFoundUserException();

        if (Objects.isNull(messageQuery.getCurrencyType()))
            throw new NotFoundUserException();

        return recipientsService.firstPage(messageQuery);
    }
}
