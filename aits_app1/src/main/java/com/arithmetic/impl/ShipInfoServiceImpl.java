package com.arithmetic.impl;

import com.arithmetic.ShipInfoService;
import com.bean.ShipInfo;
import com.dao.ShipInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipInfoServiceImpl implements ShipInfoService {

    @Autowired
    private ShipInfoRepository shipInfoRepository;



    @Override
    public ShipInfo getShipInfoByShipNo(final String shipNo) {
          ShipInfo  shipInfo= shipInfoRepository.findByShipNo(shipNo);
        return shipInfo;
    }
}
