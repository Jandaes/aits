package com.aits.service.impl;

import com.aits.dao.UserRepository;
import com.aits.entity.User;
import com.aits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 73大佬
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsernameAndPassword(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
