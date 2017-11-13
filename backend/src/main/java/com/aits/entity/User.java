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
    @NotEmpty(message = "用户名不能为空")
    private String username;
    //密码
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6,message = "密码长度不少于6位数")
    private String password;
    //用户手机
    private int phone;
    //用户邮箱
    @Email(regexp = ".@",message = "请输入正确的邮箱")
    private String email;
    //用户所属公司
    private String company;
    //用户所属部门
    private String department;
}
