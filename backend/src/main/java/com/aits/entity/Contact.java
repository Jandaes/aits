package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author jared
 * 商户信息管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {
    /**
     * 商户号
     */
    @Id
    @GeneratedValue
    private Long contactNumber;
    /**
     * 商户名
     */
    private String contactName;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 电话
     */
    private String phone;
    /**
     * 商户地址
     */
    private String address;
    /**
     * 财务信息
     * 当日收入
     * 各桌当日账单id，收到的账目总和
     */
    private String payAndGet;
    /**
     * 商户级别
     * 根据充值的金额，来升级用户等级进而能减少每月支出的金额
     */
    private String rank;
}
