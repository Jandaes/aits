package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author jared
 * 后台管理人员信息
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    /**
     * 用户id<br/>
     * 指明这个属性映射为数据库的主键<br/>
     * 注解默认使用主键声称方式为自增
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 角色id
     */
    private Long rid;
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6,message = "密码长度不少于6位数")
    private String password;
    /**
     * 用户手机
     */
    private String phone;
    /**
     * 用户邮箱
     */
    @Email(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "请输入正确的邮箱")
    private String email;
    /**
     * 用户所属公司
     */
    private String company;
    /**
     * 用户所属部门
     */
    private String department;
    /**
     * 状态：
     * 0):锁定
     * 1):正常
     */
    private Integer status;
}
