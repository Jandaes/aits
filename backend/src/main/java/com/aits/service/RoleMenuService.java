package com.aits.service;

import com.aits.entity.RoleMenu;

import java.util.List;

/**
 * 角色权限关系
 * @author Janda
 * @date 2017-12-09
 */
public interface RoleMenuService {
    List<RoleMenu> findAll();
    RoleMenu save(RoleMenu roleMenu);
}
