package com.aits.utils;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public abstract class UserContextUtil {

    public static final String AUTH_CODE_KEY ="authCode_key";

    public static Session getSession(){
        Subject currentSubjetct = SecurityUtils.getSubject();
        return currentSubjetct.getSession();
    }

    public static String getUserId(){
        return (String) getAttribute("userId");
    }

    public static String getAccount(){
        return (String) getAttribute("account");
    }

    public static String getCurrentRoleId(){
        return (String) getAttribute("roleId");
    }

    public static Object getAttribute(String key){
        return getSession().getAttribute(key);
    }

    public static void setAttribute(String key,Object value){
         getSession().setAttribute(key,value);
    }
}
