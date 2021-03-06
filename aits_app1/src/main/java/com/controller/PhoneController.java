package com.controller;

import com.arithmetic.MenuService;
import com.model.FMenuType;
import com.model.ShipMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneController {
    @Autowired
    MenuService menuService;
    /**
     * 根据店铺号，返回店铺相关信息以及菜品相关信息，封装成一个和在一个的bean中
     * @param shipNo
     * @return
     */
    @RequestMapping("/getShipInfo")
    public ShipMessage getShipInfo(String shipNo){
        ShipMessage shipMessage=menuService.getShipMessageByNo(shipNo);
        return shipMessage;
    }
    @RequestMapping("/getMenuType")
    public FMenuType getMenuType(String shipNo,Integer menuTypeId){
        FMenuType fMenuType = menuService.getMenuTypeByNoAndId(shipNo,menuTypeId);
        return fMenuType;
    }

}
