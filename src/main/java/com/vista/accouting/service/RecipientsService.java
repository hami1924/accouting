package com.vista.accouting.service;

import com.vista.accouting.dal.entity.Category;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.dal.entity.TagEntity;
import com.vista.accouting.service.models.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecipientsService {

    boolean insertRecipientsList(Message message);

    Page<Recipients> messageList(MessageQuery messageQuery);

    Recipients getById(String tagId);

    Recipients editMessageWithTag(Recipients recipients, Category category, TagEntity tag);

    Recipients editMessageWithCategory(Recipients recipients, Category category);

    DefaultPageModel firstPage(MessageQuery messageQuery);

    List<TagDefaultPageModel> listTagForDefaultPage(MessageQuery messageQuery);

}
