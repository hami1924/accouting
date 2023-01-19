package com.vista.accouting.service;

import com.vista.accouting.dal.entity.User;
import com.vista.accouting.dal.repo.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insert(User user) {

        return userRepository.save(user);
    }

    @Override
    public User get(String id) {
        return null;
    }
}
