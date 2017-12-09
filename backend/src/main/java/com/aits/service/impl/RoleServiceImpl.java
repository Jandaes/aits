package com.aits.service.impl;

import com.aits.dao.RoleRepository;
import com.aits.entity.Role;
import com.aits.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务实现类
 * @author Janda
 * @date 2017/12/9
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role role){
        return roleRepository.save(role);
    }

   /* @Override
    public List<Role> queryUserRolesByUid(Long id) {
        return roleRepository.queryUserRolesByUid(id);
    }*/
}
