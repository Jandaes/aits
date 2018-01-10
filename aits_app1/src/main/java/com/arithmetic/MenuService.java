package com.arithmetic;

import com.model.FMenuType;
import com.model.ShipMessage;

/**
 * 菜单处理
 */
public interface MenuService {
    /**
     * 根据用户扫码拿到的店铺no查到店铺以及菜品相关信息
     * @return
     */
    public ShipMessage getShipMessageByNo(String shipNo);

    /**
     * 根据用户传入的店铺no和菜品类别，返回该类别对应的所有的菜品信息
     * @param shipNo
     * @param menuTypeId
     * @return
     */
    public FMenuType getMenuTypeByNoAndId(String shipNo,int menuTypeId);
}
