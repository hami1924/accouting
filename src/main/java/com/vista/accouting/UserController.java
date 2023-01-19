package com.vista.accouting;


import com.vista.accouting.dal.entity.User;
import com.vista.accouting.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User insertUser( User user){
        return userService.insert(user);
    }

}
