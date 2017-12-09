package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jared
 * 后台管理人员角色信息
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable{
    /**
     * 角色id
     * <p>
         * 指明这个属性映射为数据库的主键
         * 注解默认使用主键声称方式为自增
     * </p>
     */
    @Id
    @GeneratedValue
    private Long rid;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标识类型
     */
    private String type;
    /**
     * 角色创建时间
     */
    private Date createTime;
    /**
     * 角色备注
     */
    private String remarks;
    /**
     * 角色状态:
     * 0：锁定
     * 1：正常
     */
    private Integer status;
}
