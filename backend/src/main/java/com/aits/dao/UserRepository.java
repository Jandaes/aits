package com.aits.dao;

import com.aits.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author jared
 */
public interface UserRepository extends JpaRepository<User,Long>{
    List<User> findByUsernameAndPassword(String username, String password);
}
