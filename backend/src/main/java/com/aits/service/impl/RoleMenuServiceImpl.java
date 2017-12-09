package com.aits.service.impl;

import com.aits.dao.RoleMenuRepository;
import com.aits.entity.RoleMenu;
import com.aits.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务实现类
 * @author Janda
 * @date 2017/12/9
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuRepository menuRepository;

    @Override
    public List<RoleMenu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public RoleMenu save(RoleMenu roleMenu) {
        return menuRepository.save(roleMenu);
    }
}
