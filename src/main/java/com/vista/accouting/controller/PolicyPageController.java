package com.vista.accouting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/page/policy")
public class PolicyPageController {

    @GetMapping
    private String policyPage(){
        return "policy";
    }
}
