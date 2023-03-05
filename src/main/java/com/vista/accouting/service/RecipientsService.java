package com.vista.accouting.service;

import com.vista.accouting.controller.dto.messageDto;

import java.util.List;

public interface RecipientsService {

    boolean insertRecipientsList(List<messageDto> list);
}
