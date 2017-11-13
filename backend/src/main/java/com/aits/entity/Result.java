package com.aits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回数据封装
 * @author Janda
 * Created by Jadna on 2017/11/13 0013.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /***
     * 提示码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体返回数据
     */
    private T data;
}
