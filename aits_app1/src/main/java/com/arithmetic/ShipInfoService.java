package com.arithmetic;

import com.bean.ShipInfo;

/**
 * 要根据电批id拿到店铺所有信息
 * @author jared
 */
public interface ShipInfoService {


    public ShipInfo getShipInfoByShipNo(String shipNo);

}
