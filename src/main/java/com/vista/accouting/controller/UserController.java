package com.vista.accouting.controller;


import com.vista.accouting.dal.entity.User;
import com.vista.accouting.exceptions.ServiceException;
import com.vista.accouting.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User insertUser( User user) throws ServiceException {
        return userService.insert(user);
    }


    @GetMapping("/{id}")
//    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler({ ChangeSetPersister.NotFoundException.class })
    public User findById(@PathVariable String id){
        Optional<User> user= userService.getById(id);
        return user.get();
    }

    @GetMapping("/{imei}/imei")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<User> findByImei(@PathVariable String imei) {
        return userService.getByImei(imei);
    }
}
