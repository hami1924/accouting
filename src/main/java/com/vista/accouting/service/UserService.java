package com.vista.accouting.service;

import com.vista.accouting.dal.entity.User;
import com.vista.accouting.exceptions.ServiceException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

public interface UserService {

    User insert(User user) ;

    public Optional<User> getById(String id);

    public Optional<User> getByImei(String imei);
}
