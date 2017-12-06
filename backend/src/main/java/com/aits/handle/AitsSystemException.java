package com.aits.handle;

import com.aits.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author D.Yang
 * @date 2017/12/6
 */
public class AitsSystemException extends RuntimeException{

    /**
     * 异常状态码
     */
    @Setter
    @Getter
    private Integer code;

    /**
     * 自定义系统异常
     * @param code 异常码
     * @param msg 异常消息
     */
    public AitsSystemException(Integer code,String msg){
        super(msg);
        this.code = code;
    }

    /**
     * 自定义异常枚举异常
     * <p>
     *     使用统一的枚举码,规范系统自定义异常消息
     * </p>
     * @param resultEnum 枚举
     */
    public AitsSystemException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}