package com.arithmetic;

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
}
