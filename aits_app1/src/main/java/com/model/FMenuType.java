package com.model;

import com.bean.BMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FMenuType {
    /**
     * 商铺号
     */
    private String ShipNo;
    @Id
    @GeneratedValue
    private int menuTypeId;

    /**
     * 菜单类型
     */
    private String menuType;

    private List<BMenu> menuList;
}
