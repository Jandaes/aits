package com.aits.shiro;

import com.aits.entity.User;
import com.aits.enums.ResultEnum;
import com.aits.handle.AitsSystemException;
import com.aits.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm 、验证、授权
 * @author 73大佬手札
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    /**
     * 1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
     * 2、@RequiresRoles("admin") ：在方法上加注解的时候；
     * 3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("principals:" + principals);
        Set<String> perms = new HashSet<String>();
        perms.add("blog:bContent:add");
        // perms.add("blog:bContent:batchRemove");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * 当调用Subject currentUser = SecurityUtils.getSubject();
     * currentUser.login(token);
     * 验证，鉴定
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        /*获取LoginController中UsernamePasswordToken*/
        String username = (String) token.getPrincipal();
        if(username == null){
            /*帐号不存在*/
            throw new AitsSystemException(ResultEnum.UNKNOWN_ACCOUNT_ERROE);
        }
        User user = new User();
        user.setUsername(username);
        /*查询帐号*/
        User userResult = userService.findByUsernameAndPassword(user);
        if(userResult == null){
            throw new AitsSystemException(ResultEnum.UNKNOWN_ACCOUNT_ERROE);
        }else {
            if(!username.equals(userResult.getUsername())){
                throw new AitsSystemException(ResultEnum.UNKNOWN_ACCOUNT_ERROE);
            }
            if(userResult.getStatus() == 0){
                throw new AitsSystemException(ResultEnum.LOCKED_ACCOUNT_ERROR);
            }
        }
        /*盐值加密*/
        ByteSource credentiallsSalt = ByteSource.Util.bytes(userResult.getUsername());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userResult,userResult.getPassword(),credentiallsSalt,getName());
        return info;
    }
}
