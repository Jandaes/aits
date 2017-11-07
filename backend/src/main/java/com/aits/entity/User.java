package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author jared
 * 后台管理人员信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    //指明这个属性映射为数据库的主键
    @Id
    //注解默认使用主键声称方式为自增
    @GeneratedValue
    //用户id
    private Long id;
    //角色id
    private Long rid;
    //用户名
    private String username;
    //密码
    private String password;
    //用户手机
    private int phone;
    //用户邮箱
    private String email;
    //用户所属公司
    private String company;
    //用户所属部门
    private String department;
}
