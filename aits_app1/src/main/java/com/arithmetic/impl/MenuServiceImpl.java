package com.arithmetic.impl;

import com.alibaba.fastjson.JSONObject;
import com.arithmetic.MenuService;
import com.arithmetic.ShipInfoService;
import com.bean.BMenu;
import com.bean.MenuType;
import com.bean.ShipInfo;
import com.dao.BMenuRepository;
import com.dao.MenuTypeRepository;
import com.model.FMenuType;
import com.model.ShipMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private BMenuRepository bMenuRepository;
    @Autowired
    private ShipInfoService shipInfoService;
    @Autowired
    private MenuTypeRepository menuTypeRepository;

    @Override
    public ShipMessage getShipMessageByNo(String shipNo) {
        ShipMessage shipMessage = new ShipMessage();
        List<FMenuType> fMenuTypes = new ArrayList<>();
        List<BMenu> list = bMenuRepository.findByBShipNo(shipNo);

        ShipInfo shipInfo = shipInfoService.getShipInfoByShipNo(shipNo);
        List<MenuType> menuTypes = menuTypeRepository.findByShipNo(shipNo);

        for (int i = 0; i < menuTypes.size(); i++) {
            FMenuType fMenuType = new FMenuType();
            fMenuType.setMenuType(menuTypes.get(i).getMenuType());
            fMenuType.setMenuTypeId(menuTypes.get(i).getMenuTypeId());
            fMenuType.setShipNo(menuTypes.get(i).getShipNo());
            String[] menuIds = menuTypes.get(i).getMenuIds().split(",");
            List<BMenu> bMenus = new ArrayList<>();
            if (i == 0) {
                for (int j = 0; j < menuIds.length; j++) {
                    BMenu bMenu = bMenuRepository.findByBMenuId(menuIds[j]);
                    bMenus.add(bMenu);
                }
                fMenuType.setMenuList(bMenus);
            }
            fMenuTypes.add(fMenuType);
        }

        shipMessage.setFMenuTypes(fMenuTypes);
        shipMessage.setAddress(shipInfo.getAddress());
        shipMessage.setIcoPath(shipInfo.getIcoPath());
        shipMessage.setShipName(shipInfo.getShipName());
        shipMessage.setShowImgPath(shipInfo.getShowImgPath());
        shipMessage.setShipNo(shipInfo.getShipNo());
        return shipMessage;
    }


    @Override
    public FMenuType getMenuTypeByNoAndId(String shipNo, int menuTypeId) {
        MenuType menuType = menuTypeRepository.findByShipNoAndMenuTypeId(shipNo, menuTypeId);
        String[] menuIds = menuType.getMenuIds().split(",");
        FMenuType fMenuType = new FMenuType();
        fMenuType.setMenuType(menuType.getMenuType());
        fMenuType.setMenuTypeId(menuType.getMenuTypeId());
        fMenuType.setShipNo(menuType.getShipNo());
        List<BMenu> bMenus = new ArrayList<>();
        for (int j = 0; j < menuIds.length; j++) {
            BMenu bMenu = bMenuRepository.findByBMenuId(menuIds[j]);
            bMenus.add(bMenu);
        }
        fMenuType.setMenuList(bMenus);
        return fMenuType;
    }





}
