package com.vista.accouting.controller;

import com.vista.accouting.controller.adpter.MessageDtoAdapter;
import com.vista.accouting.controller.dto.MessageDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/recipient")
public class RecipientsController extends BaseController{

    private final MessageDtoAdapter messageDtoAdapter;

    public RecipientsController(MessageDtoAdapter messageDtoAdapter) {
        this.messageDtoAdapter = messageDtoAdapter;
    }

    @PostMapping
    public Boolean insertMessage(@RequestBody MessageDto messageDto){

        return  messageDtoAdapter.insertMessageDto(messageDto);
    }
}
