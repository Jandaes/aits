package com.aits.dao;

import com.aits.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jared
 */
public interface UserRepository extends JpaRepository<User,Long>{
    /**
     * 根据帐号密码查询用户<br/>
     * 要保持数据库帐号唯一性
     * @param username 帐号
     * @param password 密码
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
