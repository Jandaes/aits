package com.aits.shiro;

import com.aits.entity.User;
import com.aits.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
        String username = (String) token.getPrincipal();
        if(username == null){
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        String password = new String((char[]) token.getCredentials());
        Object result = new SimpleHash("MD5", password,username, 1024);
        User user = new User();
        user.setUsername(username);
        user.setPassword(result.toString());
        List<User> list = userService.findByUsernameAndPassword(user);
        user = new User();
        if(list.size()>0){
            user = list.get(0);
            if (!user.getUsername().equals(username)){
                throw new UnknownAccountException("帐号或密码错误");
            }
        }else {
            throw new UnknownAccountException("用户不存在");
        }

        //盐值加密
        ByteSource credentiallsSalt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, result.toString(), credentiallsSalt, getName());
        return info;
    }
}
