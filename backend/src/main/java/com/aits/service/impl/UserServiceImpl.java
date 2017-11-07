package com.aits.service.impl;

import com.aits.dao.UserRepository;
import com.aits.entity.User;
import com.aits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findByUsernameAndPassword(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
