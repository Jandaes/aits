package com.aits.service;

import com.aits.entity.UserRole;

import java.util.List;

/**
 * @author Janda
 * @date 2017-12-09
 */
public interface UserRoleService {

    List<UserRole> findAll();
    UserRole save(UserRole userRole);
}
