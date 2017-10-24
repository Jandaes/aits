package com.liu.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    /**
     * 1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
     2、@RequiresRoles("admin") ：在方法上加注解的时候；
     3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
     授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("principals:"+principals);
        Set<String> perms=new HashSet<String>();
        perms.add("blog:bContent:add");
       // perms.add("blog:bContent:batchRemove");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * 当调用Subject currentUser = SecurityUtils.getSubject();
     currentUser.login(token);
     验证，鉴定
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       String username= (String) token.getPrincipal();
        System.out.println("uname:"+username);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("username",username);
        String password=new String((char[]) token.getCredentials());
        System.out.println("pass=:"+password);
        System.out.println("getName:"+getName());
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,password,getName());
        return info;
    }
}
