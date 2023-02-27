package com.vista.accouting.service;

import com.vista.accouting.dal.entity.User;
import com.vista.accouting.dal.repo.UserRepository;
import com.vista.accouting.exceptions.ServiceException;
import com.vista.accouting.exceptions.ServiceExceptionType;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insert(User user) throws ServiceException {
        List<User> userOld=userRepository.findByMobileOrImei(user.getMobile(),user.getImei());
        if (userOld.isEmpty())
             throw new ServiceException("User is exist",ServiceExceptionType.Bad_Request);
        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public Optional<User>  getById(String id){
      return Optional.ofNullable(userRepository.findById(new ObjectId(id)).orElseThrow(() -> new ServiceException("not founf User", ServiceExceptionType.Not_Found)));
    }

    @Override
    @SneakyThrows
    public Optional<User> getByImei(String imei) {
        return Optional.ofNullable(userRepository.findByImei(imei).orElseThrow(() -> new ServiceException("not founf User", ServiceExceptionType.Not_Found)));
    }
}
