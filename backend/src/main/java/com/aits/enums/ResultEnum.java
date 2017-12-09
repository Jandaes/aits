package com.aits.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常信息枚举
 *
 * @author Janda
 * @date 2017/11/13 0013
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    /**
     *  未知错误
     */
    UNKONW_ERROR(-1,"未知错误"),
    /**
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * 错误
     */
    ERROR(201,"错误"),


    /**
     * 无权限操作
     */
    FORBIDDEN_ERROR(403,"无权限操作"),
    /**
     * 帐号异常
     */
    ACCOUNT_ERROR(10000,"帐号异常"),
    /**
     * 未知账号
     */
    UNKNOWN_ACCOUNT_ERROE(10001,"未知账号"),
    /**
     * 帐号已经锁定
     */
    LOCKED_ACCOUNT_ERROR(10002,"帐号已经锁定"),
    /**
     * 帐号被禁用
     */
    DISABLED_ACCOUNT_ERROR(10003,"帐号被禁用"),
    /**
     * 登录重试次数超限
     */
    EXCESSIVE_ATTEMPTS_ERROR(10004,"登录重试次数超限"),
    /**
     * 过期凭据
     */
    EXPIRED_CREDENTIALS_ERROR(10005,"过期凭据"),
    /**
     * 错误的凭据
     */
    INCORRECT_CREDENTIALS_ERROR(10006,"错误的凭据"),
    /**
     * 未登录
     */
    NOT_LOGGED_IN_ERROR(10007,"未登录"),


    ;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态信息
     */
    private String msg;

}
