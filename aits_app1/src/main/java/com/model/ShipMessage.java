package com.model;

import com.bean.BMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jared
 * model里的内容为给前端封装的信息
 * 该信息为用户浏览器，扫描了桌子上的二维码的信息以后，返回的信息。即店铺信息和菜品信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipMessage {
    /**
     * 店铺号
     */
    private String shipNo;
    /**
     * 店铺名
     */
    private String shipName;
    /**
     * 店铺地址
     */
    private String address;

    /**
     * 商家电话
     */
    private String phone;

    /**
     * 店家logo(提供一个自动截取图片的插件)
     */
    private String icoPath;

    /**
     * 展示店铺信息如，外景、内景，主要菜品
     */
    private String showImgPath;
    /**
     * 根据店铺id返回的所有店铺相关菜品
     */
    private List<FMenuType> fMenuTypes;


}
