package com.aits.service.impl;

import com.aits.dao.MenuRepository;
import com.aits.entity.Menu;
import com.aits.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限资源服务实现类
 * @author Janda
 * @date 2017/12/9
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }
}
