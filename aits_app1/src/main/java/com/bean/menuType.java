package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bmenuType")
public class menuType {
    /**
     * 商铺号
     */
    private String ShipNo;
    /**
     * 菜单类型
     */
    private String menuType;

    private String menuIds;

}
