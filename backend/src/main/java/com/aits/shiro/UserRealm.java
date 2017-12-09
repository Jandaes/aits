package com.aits.shiro;

import com.aits.entity.User;
import com.aits.enums.ResultEnum;
import com.aits.handle.AitsSystemException;
import com.aits.service.RoleService;
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

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm 、验证、授权
 * @author 73大佬手札
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
     * 2、@RequiresRoles("admin") ：在方法上加注解的时候；
     * 3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
     * 需要权限才能执行到这里
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*获取当前授权的用户*/
        User user = (User)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*
            动态读取数据权限
            List<Role> roleList = roleService.queryUserRolesByUid(user.getId());
            roleList.forEach(role ->{
                System.out.println("我的："+ role.toString());
            });
        */
        Set<String> roles = new HashSet<>();
        /*设置默认角色*/
        roles.add("0001");

        Set<String> perms = new HashSet<>();
        /*
            设置权限、同
            chains.put("/role/list", "perms[role:list]")
            一起使用
        */
        perms.add("role:list");

        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * 当调用Subject currentUser = SecurityUtils.getSubject();
     * currentUser.login(token);
     * 验证，鉴定
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
        User user = userService.findByUsername(username);
        if(user == null){
            /*
            * 具体详细错误查看：ResultEnum.UNKNOWN_ACCOUNT_ERROE
            * */
            throw new AitsSystemException(ResultEnum.UNKNOWN_ACCOUNT_ERROE);
        }else {
            if(!username.equals(user.getUsername())){
                throw new AitsSystemException(ResultEnum.UNKNOWN_ACCOUNT_ERROE);
            }
            if(user.getStatus() == 0){
                throw new AitsSystemException(ResultEnum.LOCKED_ACCOUNT_ERROR);
            }
        }
        /*盐值加密*/
        ByteSource credentiallsSalt = ByteSource.Util.bytes(user.getUsername());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),credentiallsSalt,getName());
        System.out.println("登录成功"+user.toString());
        return info;
    }

    public static void main(String[] args) {
        /*
         * admin
         *   038bdaf98f2037b31f1e75b5b4c9b26e
         * test
         *   4292bb58be34c59d28a0dcbd11932d49
         * */
        //加密方法
        String algorithmName = "MD5";
        String source = "admin";
        //盐值加密
        String salt = "admin";
        //加密次数
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName, source,salt, hashIterations);
        System.out.println(result);
    }
}
