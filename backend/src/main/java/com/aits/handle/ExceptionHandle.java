package com.aits.handle;

import com.aits.entity.Result;
import com.aits.enums.ResultEnum;
import com.aits.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常捕获自定义
 * <p>
 *     捕获 异常操作、且ResultUtil  封装返回
 * </p>
 * @author Janda
 * Created by Janda on 2017/11/13 0013.
 */
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 异常日志记录
     */
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 捕获Exception 异常
     * @param ex 异常信息
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception ex){
        /*自定义AitsSystemException异常,如有更多自定义异常可追加判断*/
        if(ex instanceof AitsSystemException){
            logger.error("【AitsSystemException 异常消息：】",ex);
            AitsSystemException aitsSystemException = (AitsSystemException)ex;
            return ResultUtil.error(aitsSystemException.getCode(),aitsSystemException.getMessage());
        }else {
            logger.error("【JDK 异常】",ex);
            return ResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
    }
}
