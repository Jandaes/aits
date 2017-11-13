package com.aits.utils;

import com.aits.entity.Result;

/**
 * 返回数据工具类
 * Created by D.Yang on 2017/11/13 0013.
 */
public class ResultUtil {

    /**
     * 成功返回数据
     * @param obj
     * @return
     */
    public static Result success(Object obj){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(obj);
        return result;
    }

    /**
     * 返回成功不带参数
     * @return
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 返回错误
     * @param code 错误代码
     * @param msg 错误信息
     * @return
     */
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回错误不带参数
     * @return
     */
    public static Result error(){
        return error(null,null);
    }
}
