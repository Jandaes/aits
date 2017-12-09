package com.aits.service.impl;

import com.aits.dao.UserRoleRepository;
import com.aits.entity.UserRole;
import com.aits.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Janda
 * @date 2017/12/9
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository repository;

    @Override
    public List<UserRole> findAll() {
        return repository.findAll();
    }

    @Override
    public UserRole save(UserRole userRole) {
        return repository.save(userRole);
    }
}
