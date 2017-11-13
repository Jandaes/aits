package com.aits.handle;

import com.aits.entity.Result;
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
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        logger.error("【系统异常】{}",e);
        return ResultUtil.error(300,e.getMessage());
    }
}
