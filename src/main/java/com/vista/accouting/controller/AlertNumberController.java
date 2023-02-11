package com.vista.accouting.controller;

import com.vista.accouting.dal.entity.SmsNumberAlert;
import com.vista.accouting.enums.BanksEnum;
import com.vista.accouting.enums.CountryEnums;
import com.vista.accouting.service.SmsNumberAlertService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alert-number")
public class AlertNumberController extends BaseController{

    private final SmsNumberAlertService alertService;

    public AlertNumberController(SmsNumberAlertService alertService) {
        this.alertService = alertService;
    }


    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SmsNumberAlert insert(SmsNumberAlert smsNumberAlert){
       return alertService.insert(smsNumberAlert);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SmsNumberAlert getById(@PathVariable String id){
        return alertService.get(id).get();
    }

    @GetMapping(value = {"/{bank}/{country}","/bank/{bank}","/country/{country}"})
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<SmsNumberAlert> listByParameter(@PathVariable(required = false) BanksEnum bank, @PathVariable(required = false) CountryEnums country){
        return alertService.list(country,bank);
    }
}
