package com.aits.dao;

import com.aits.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Janda
 * @date 2017/12/9
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
}
