package com.aits.service;

import com.aits.entity.Role;

import java.util.List;

/**
 * 角色表服务
 * @author Janda
 * @date 2017-12-09
 */
public interface RoleService{

    List<Role> findAll();
    Role save(Role role);
}
