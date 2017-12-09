package com.aits.dao;

import com.aits.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 权限资源表
 * @author Janda
 * @date 2017/12/9
 */
public interface MenuRepository extends JpaRepository<Menu,Long> {
}
