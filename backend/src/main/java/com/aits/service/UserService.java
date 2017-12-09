package com.aits.service;

import com.aits.entity.User;

/**
 * @author jared
 */
public interface UserService {
    /**
     * 根据帐号密码查询用户<br/>
     * 要保持数据库帐号唯一性
     * @param user
     * @return
     */
    User findByUsernameAndPassword(User user);

    /**
     * 添加用户
     * @param user
     */
    void save(User user);

    /**
     * 根据帐号查询
     * @param username
     * @return
     */
    User findByUsername(String username);
}
