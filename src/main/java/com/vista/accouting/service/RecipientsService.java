package com.vista.accouting.service;

import com.vista.accouting.service.models.Message;
import com.vista.accouting.service.models.MessageQuery;
import com.vista.accouting.service.models.MessageView;

import java.util.List;

public interface RecipientsService {

    boolean insertRecipientsList(Message message);

    List<MessageView> messageList(MessageQuery messageQuery);

}
