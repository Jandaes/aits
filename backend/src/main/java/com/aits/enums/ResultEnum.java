package com.aits.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常信息枚举
 * Created by Janda on 2017/11/13 0013.
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(200,"成功"),
    ERROR(201,"错误");

    private Integer code;
    private String msg;

}
