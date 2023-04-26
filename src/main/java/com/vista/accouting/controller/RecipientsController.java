package com.vista.accouting.controller;

import com.vista.accouting.controller.adpter.MessageDtoAdapter;
import com.vista.accouting.controller.dto.EditRecipientCategoryDto;
import com.vista.accouting.controller.dto.EditRecipientTagDto;
import com.vista.accouting.controller.dto.MessageDto;
import com.vista.accouting.dal.entity.Recipients;
import com.vista.accouting.service.models.DefaultPageModel;
import com.vista.accouting.service.models.MessageQuery;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping(path = "/recipient")
public class RecipientsController extends BaseController{

    private final MessageDtoAdapter messageDtoAdapter;

    public RecipientsController(MessageDtoAdapter messageDtoAdapter) {
        this.messageDtoAdapter = messageDtoAdapter;
    }

    @PostMapping
    @ExceptionHandler({ ChangeSetPersister.NotFoundException.class })

    public Boolean insertMessage(@RequestBody MessageDto messageDto){

        return  messageDtoAdapter.insertMessageDto(messageDto);
    }

    @PostMapping("/messages")
    @ExceptionHandler({  AccountNotFoundException.class})
    public Page<Recipients> listMessage(@RequestBody MessageQuery messageQuery)  {
        return messageDtoAdapter.listMessage(messageQuery);
    }

    @PutMapping("/message")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Recipients editRecipientsWithTag(@RequestBody EditRecipientTagDto editRecipientTagDto){
        return messageDtoAdapter.editRecipientsTagAndCategory(editRecipientTagDto);
    }

    @PutMapping("/message/category")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Recipients editRecipientsWithCategory(@RequestBody EditRecipientCategoryDto editRecipientCategoryDto){
        return messageDtoAdapter.editRecipientsCategory(editRecipientCategoryDto);
    }

    @PostMapping("/default/page")
    public DefaultPageModel defaultPage(@RequestBody MessageQuery messageQuery)  {
        return messageDtoAdapter.defaultPage(messageQuery);
    }


}
