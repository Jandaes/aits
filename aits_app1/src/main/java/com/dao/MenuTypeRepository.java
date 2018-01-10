package com.dao;

import com.bean.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuTypeRepository extends JpaRepository<MenuType,Long>{
    List<MenuType> findByShipNo(String shipNo);
    MenuType findByShipNoAndMenuTypeId(String shipNo,int menuTypeId);
}
