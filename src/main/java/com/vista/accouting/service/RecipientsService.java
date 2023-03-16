package com.vista.accouting.service;

import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.service.models.DefaultPageModel;
import com.vista.accouting.service.models.Message;
import com.vista.accouting.service.models.MessageQuery;
import com.vista.accouting.service.models.MessageView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecipientsService {

    boolean insertRecipientsList(Message message);

    Page<Recipients> messageList(MessageQuery messageQuery);

    DefaultPageModel firstPage(MessageQuery messageQuery);

}
