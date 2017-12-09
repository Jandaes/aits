package com.aits.controller;

import com.aits.entity.Result;
import com.aits.entity.Role;
import com.aits.service.RoleService;
import com.aits.utils.ResultUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色操作
 * @author Janda
 * @date 2017/12/10
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController{
    private Logger logger = Logger.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    /**
     * 查询全部角色列表
     * @return
     */
    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public Result list(){
        List<Role> roleList = roleService.findAll();
        logger.info("是否有登录："+ SecurityUtils.getSubject().isAuthenticated());
        logger.info(roleList);
        return ResultUtil.success(roleList);
    }
}
