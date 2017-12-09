package com.aits.dao;

import com.aits.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色DAO
 * @author Janda
 * @date 2017/12/9
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    /**
     * 根据用户编号、查询对应的所有角色
     * @param id 用户编号
     * @return
     */
    //List<Role> queryUserRolesByUid(Long id);
}
