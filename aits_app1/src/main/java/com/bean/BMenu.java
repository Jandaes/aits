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
@Entity(name = "bmenu")
public class BMenu {
    /**
     * 商铺号
     */
    private String bShipNo;
    /**
     * 菜品id
     */
    @Id
 //   @GeneratedValue
    private String bMenuId;
    /**
     * 菜品名称
     */
    private String bMenuName;
    /**
     * 菜品价格
     */
    private Double bPrice;
    /**
     * 菜品折扣后价格
     */
    private Double bDiscountPrice;
    /**
     * 折扣
     */
    private Double bDiscount;

    /**
     * 折扣时间
     */
    private String bDiscountTime;



}
