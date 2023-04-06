package com.vista.accouting.controller;


import com.vista.accouting.dal.entity.User;
import com.vista.accouting.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController extends BaseController{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public User insertUser( User user)  {
        return userService.insert(user);
    }


    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable String id){
        Optional<User> user= userService.getById(id);
        return user.get();
    }

    @GetMapping("/{imei}/imei")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> findByImei(@PathVariable String imei) {
        return userService.getByImei(imei);
    }
}
