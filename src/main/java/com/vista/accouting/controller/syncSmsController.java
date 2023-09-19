package com.vista.accouting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/sync")
public class syncSmsController {


    @GetMapping("/active/version")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean get()  {
        return false;
    }
}
