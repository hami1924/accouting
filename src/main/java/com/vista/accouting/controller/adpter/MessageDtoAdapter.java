package com.vista.accouting.controller.adpter;

import com.vista.accouting.controller.dto.ContentDto;
import com.vista.accouting.controller.dto.EditRecipientCategoryDto;
import com.vista.accouting.controller.dto.EditRecipientTagDto;
import com.vista.accouting.controller.dto.MessageDto;
import com.vista.accouting.controller.mapper.MessageDtoMapper;
import com.vista.accouting.dal.entity.Category;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.dal.entity.User;
import com.vista.accouting.exceptions.NotFoundUserException;
import com.vista.accouting.service.CategoryService;
import com.vista.accouting.service.RecipientsService;
import com.vista.accouting.service.TagService;
import com.vista.accouting.service.UserService;
import com.vista.accouting.service.models.DefaultPageModel;
import com.vista.accouting.service.models.Message;
import com.vista.accouting.service.models.MessageQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class MessageDtoAdapter {
    private final RecipientsService recipientsService;

    private final UserService userService;

    private final TagService tagService;

    private final CategoryService categoryService;


    public MessageDtoAdapter(RecipientsService recipientsService, UserService userService, TagService tagService, CategoryService categoryService) {
        this.recipientsService = recipientsService;
        this.userService = userService;
        this.tagService = tagService;
        this.categoryService = categoryService;
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

        if (Objects.nonNull(messageQuery.getTag())){
            messageQuery.setCategory(messageQuery.getTag());
            messageQuery.setTag(null);
        }
        return recipientsService.messageList(messageQuery);
    }

    public Recipients editRecipientsTagAndCategory(EditRecipientTagDto editRecipientTagDto){

        Optional<User> user=userService.getById(editRecipientTagDto.getUserId());

        Recipients recipients=recipientsService.getById(editRecipientTagDto.getMessageId());

        TagEntity tag=tagService.findById(editRecipientTagDto.getTagId());

        Category category=categoryService.findById(tag.getCategoryId());

        return recipientsService.editMessageWithTag(recipients,category,tag);
    }

    public Recipients editRecipientsCategory(EditRecipientCategoryDto editRecipientCategoryDto){

        Optional<User> user=userService.getById(editRecipientCategoryDto.getUserId());

        Recipients recipients=recipientsService.getById(editRecipientCategoryDto.getMessageId());

        Category category=categoryService.findById(editRecipientCategoryDto.getCategoryId());

        return recipientsService.editMessageWithCategory(recipients,category);
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
