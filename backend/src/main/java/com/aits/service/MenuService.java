package com.aits.service;

import com.aits.entity.Menu;

import java.util.List;

/**
 * 权限资源管理
 * @author Janda
 * @date 2017-12-09
 */
public interface MenuService {
    List<Menu> findAll();
    Menu save(Menu menu);
}
