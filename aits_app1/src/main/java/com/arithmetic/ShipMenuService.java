package com.arithmetic;

public interface ShipMenuService {
    /**
     * 添加菜品信息
     * @param xx
     * @return
     */
    public boolean addMenu(String xx);

    /**
     * 添加菜品类别信息
     * @param yyy
     * @return
     */
    public boolean addMenuType(String yyy);

    public boolean getMsh();
}
