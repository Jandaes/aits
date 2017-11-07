package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author jared
 * 左侧菜单管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {
    //指明这个属性映射为数据库的主键
    @Id
    //注解默认使用主键声称方式为自增
    @GeneratedValue
    //菜单id
    private Long mid;
    //菜单名称
    private String menuname;
    //菜单路径
    private String menupath;
    //菜单对应icon
    private String icon;
    //菜单对应角色
    private Long rid;
}
