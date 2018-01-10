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
@Entity(name = "menuType")
public class MenuType {
    /**
     * 商铺号
     */
    private String shipNo;
    @Id
    @GeneratedValue
    private int menuTypeId;

    /**
     * 菜单类型
     */
    private String menuType;
    /**
     * 对应的所有菜品id
     */
    private String menuIds;

}
