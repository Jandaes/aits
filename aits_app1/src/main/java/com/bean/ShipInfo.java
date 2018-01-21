package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shipInfo")
public class ShipInfo {
    /**
     * 店铺号
     */
    @Id
  //  @GeneratedValue
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
     * 展示店铺信息如，外景、内景，主要菜品,多张图用逗号隔开
     */
    private String showImgPath;




}
