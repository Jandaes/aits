package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author jared
 * 左侧菜单管理
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    /**
     * 菜单编号
     * 主键，自增
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单路径
     */
    private String menuUrl;
    /**
     * 菜单对应icon
     */
    private String icon;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 权限串
     */
    private String perms;
    /**
     * 所属上级编号
     */
    private Long parentId;
    /**
     * 排序
     */
    private Integer sort;
}
