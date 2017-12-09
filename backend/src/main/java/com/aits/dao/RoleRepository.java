package com.aits.dao;

import com.aits.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色DAO
 * @author Janda
 * @date 2017/12/9
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
}
