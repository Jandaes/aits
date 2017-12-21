package com.aits.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 需要config配置类配合，看config目录
 */
public class MyInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //controller方法调用之前
        System.out.println("我没进controller");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //请求处理之后进行调用，但是在视图被渲染之前，即Controller方法调用之后
        System.out.println("我进拦截器了");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行，主要是用于进行资源清理工作
    }
}
