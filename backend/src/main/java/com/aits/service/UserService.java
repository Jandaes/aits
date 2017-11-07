package com.aits.service;

import com.aits.entity.User;

import java.util.List;

/**
 * @author jared
 */
public interface UserService {
    public List<User> findByUsernameAndPassword(User user);
}
