package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity(name = "BOrder")
public class BOrder {
    /**
     * 商铺号
     */
    private String shipNo;

    /**
     * 订单id
     */
    @Id
    @GeneratedValue
    //这里的自增有问题
    private int orderNum;

    /**
     * 桌号（用来定位上菜桌号）
     */
    private String pTableId;
    /**
     * 所点菜品
     */
    private List<PhoneMenu> pMenuList;

    /**
     * 菜品总价格
     */
    private Double pSumPrice;


}
