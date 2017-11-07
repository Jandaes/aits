package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author jared
 * 后台管理人员角色信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    //指明这个属性映射为数据库的主键
    @Id
    //注解默认使用主键声称方式为自增
    @GeneratedValue
    //角色id
    private Long rid;
    //角色名称
    private String rolename;
}
