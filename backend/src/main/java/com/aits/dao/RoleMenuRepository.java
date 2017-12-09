package com.aits.dao;

import com.aits.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色权限关系表
 * @author Janda
 * @date 2017/12/9
 */
public interface RoleMenuRepository extends JpaRepository<RoleMenu,Long> {}
