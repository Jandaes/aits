package com.dao;

import com.bean.ShipInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipInfoRepository extends JpaRepository<ShipInfo,Long> {
    ShipInfo findByShipNo(String shipNo);
}
