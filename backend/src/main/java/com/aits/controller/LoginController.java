package com.aits.controller;

import com.aits.entity.Result;
import com.aits.entity.User;
import com.aits.enums.ResultEnum;
import com.aits.handle.AitsSystemException;
import com.aits.utils.JaredRedisTemplate;
import com.aits.utils.JsonUtil;
import com.aits.utils.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录控制类
 *
 * @author Janda
 * @date 2017/12/7
 */
@RestController
@RequestMapping(value = "/")
public class LoginController extends BaseController {
    /**
     * 日志打印
     */
    private Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private JaredRedisTemplate redisTemplate;

    /**
     * 用户登录Shiro控制
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user, HttpSession session, HttpServletResponse httpServletResponse) {
        System.out.println("sessionL" + session.getId());
        System.out.println("user:++++" + SecurityUtils.getSubject().getSession().getId());
//        Cookie cookie = new Cookie("SESSION", session.getId());
//        cookie.setMaxAge(10); //设置cookie的过期时间是10s
//        httpServletResponse.addCookie(cookie); //.addCookie();
        String userMassage = JSONObject.toJSONString(user);
        redisTemplate.set("", session.getId(), userMassage);
        logger.info(user.toString());
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
                System.out.println("token:" + token.toString());
                session.setAttribute("673" + session.getId(), token);
                /*交给UserRealm 验证真实性*/
                subject.login(token);
            } catch (AitsSystemException e) {
                logger.error("登录失败 {0}", e);
                return ResultUtil.error(e.getCode(), e.getMessage());
            }
            return ResultUtil.success();
        } else {
            logger.info("已经登录过了,跳转至首页");
            return ResultUtil.Cookiesuccess(session.getId());
        }
    }

    /**
     * 未登陆跳转到这里
     *
     * @return
     */
    @RequestMapping(value = "/unlogin", method = RequestMethod.GET)
    public void unLogin() {
        logger.info("请先登录");
        throw new AitsSystemException(ResultEnum.NOT_LOGGED_IN_ERROR);
    }

    /**
     * 未授权将会跳转到这里执行
     *
     * @return
     */
    @RequestMapping(value = "/forbidden", method = RequestMethod.GET)
    public void unauthorizedUrl() {
        logger.info("无权限操作");
        throw new AitsSystemException(ResultEnum.FORBIDDEN_ERROR);
    }
}
