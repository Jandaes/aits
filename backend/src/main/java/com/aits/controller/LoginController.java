package com.aits.controller;

import com.aits.entity.Result;
import com.aits.entity.User;
import com.aits.handle.AitsSystemException;
import com.aits.utils.ResultUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制类
 * @author Janda
 * @date 2017/12/7
 */
@RestController
@RequestMapping(value = "/")
public class LoginController extends BaseController{
    /**日志打印*/
    private Logger logger = Logger.getLogger(LoginController.class);

    /**
     * 用户登录Shiro控制
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        logger.info(user.toString());
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
                /*交给UserRealm 验证真实性*/
                subject.login(token);
            } catch (AitsSystemException e) {
                logger.error("登录失败 {0}",e);
                return ResultUtil.error(e.getCode(),e.getMessage());
            }
            return ResultUtil.success();
        }else {
            logger.info("已经登录过了,跳转至首页");
            return ResultUtil.success();
        }
    }
}
