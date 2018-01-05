package com.dao;

import com.bean.BMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author jared
 */
public interface BMenuRepository  extends JpaRepository<BMenu,Long>{
    List<BMenu> findByBShipNo(String bShipNo);
}
