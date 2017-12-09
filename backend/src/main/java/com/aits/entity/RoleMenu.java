package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author jared
 * 菜单角色关系表
 */
@Data
@Entity(name = "role_menu")
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu implements Serializable{
    /**
     * 编号自增
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 菜单编号
     */
    private Long mId;
    /**
     * 角色编号
     */
    private Long rId;
}
