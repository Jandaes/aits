package com.aits.listener;
;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 需要主类加入
 * @ServletComponentScan
 */
@WebListener
public class FirstListener implements ServletContextListener{
    private static Logger LOG= LoggerFactory.getLogger(FirstListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("firstListener  初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("FirstListener  销毁...");
    }
}
