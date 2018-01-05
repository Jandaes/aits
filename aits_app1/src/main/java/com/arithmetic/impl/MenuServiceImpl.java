package com.arithmetic.impl;

import com.arithmetic.MenuService;
import com.arithmetic.ShipInfoService;
import com.bean.BMenu;
import com.bean.ShipInfo;
import com.dao.BMenuRepository;
import com.model.ShipMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private BMenuRepository bMenuRepository;
    @Autowired
    private ShipInfoService shipInfoService;

    @Override
    public ShipMessage getShipMessageByNo(String shipNo) {
        ShipMessage shipMessage = new ShipMessage();
        List<BMenu> list= bMenuRepository.findByBShipNo(shipNo);
        ShipInfo shipInfo=shipInfoService.getShipInfoByShipNo(shipNo);
        shipMessage.setMenuList(list);
        shipMessage.setAddress(shipInfo.getAddress());
        shipMessage.setIcoPath(shipInfo.getIcoPath());
        shipMessage.setShipName(shipInfo.getShipName());
        shipMessage.setShowImgPath(shipInfo.getShowImgPath());
        shipMessage.setShipNo(shipInfo.getShipNo());
        return shipMessage;
    }
}
