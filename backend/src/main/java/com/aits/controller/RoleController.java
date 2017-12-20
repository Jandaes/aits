package com.aits.controller;

import com.aits.entity.Result;
import com.aits.entity.Role;
import com.aits.entity.User;
import com.aits.enums.ResultEnum;
import com.aits.handle.AitsSystemException;
import com.aits.service.RoleService;
import com.aits.utils.JaredRedisTemplate;
import com.aits.utils.JsonUtil;
import com.aits.utils.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * 角色操作
 *
 * @author Janda
 * @date 2017/12/10
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    private Logger logger = Logger.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private JaredRedisTemplate redisTemplate;

    /**
     * 查询全部角色列表
     *
     * @return
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public Result list(HttpServletRequest req, String token, HttpSession session, HttpServletResponse httpServletResponse) {
        String userMessage = redisTemplate.get("", token);
        userMessage=userMessage.replace("\\", "");
        User user = JSONObject.parseObject(userMessage,User.class);
        UsernamePasswordToken msgToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        SecurityUtils.getSubject().login(msgToken);
        List<Role> roleList = roleService.findAll();
        boolean isLogin = SecurityUtils.getSubject().isAuthenticated();

        logger.info("是否有登录：" + isLogin);
        logger.info(roleList);
        if (isLogin) {
            logger.info("无权限操作");
            throw new AitsSystemException(ResultEnum.FORBIDDEN_ERROR);
        }
        return ResultUtil.success(roleList);
    }
}
