package com.vista.accouting.service;

import com.vista.accouting.dal.entity.User;

public interface UserService {

    User insert(User user);

    User get(String id);
}
