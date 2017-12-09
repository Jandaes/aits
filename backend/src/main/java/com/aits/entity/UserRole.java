package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户和角色关联表
 * @author Janda
 * @date 2017/12/9
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    /**
     * 编号自增
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 角色编号
     */
    private Long roleId;
    /**
     * 用户编号
     */
    private Long userId;
}
