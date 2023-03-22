package com.vista.accouting.service;

import com.vista.accouting.dal.entity.User;
import com.vista.accouting.dal.repo.UserRepository;
import com.vista.accouting.exceptions.NotFoundUserException;
import com.vista.accouting.exceptions.ServiceException;
import com.vista.accouting.exceptions.ServiceExceptionType;
import com.vista.accouting.exceptions.UserFoundException;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insert(User user)  {
        List<User> userOld=userRepository.findByMobileOrImei(user.getMobile(),user.getImei());
        if (userOld.size()>1)
             throw new UserFoundException();
        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public Optional<User>  getById(String id){
      return Optional.ofNullable(userRepository.findById(new ObjectId(id)).orElseThrow(() ->  new NotFoundUserException()));
    }

    @Override
    @SneakyThrows
    public Optional<User> getByImei(String imei) {
        return Optional.ofNullable(userRepository.findByImei(imei).orElseThrow(() ->  new NotFoundUserException()));
    }
}
