package com.aits.config;

import com.aits.shiro.UserRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @author jared
 */
@Configuration
public class ShiroConfig implements EnvironmentAware {
    private Environment env;
    @Value("${ shiro.anon.urls }")
    private String anonUrls;

    /**
     * 开启shiro注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 权限管理器
     *
     * @param userRealm
     * @return
     */
    @Bean
    SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm);
//		<!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        manager.setCacheManager(getEhCacheManager());
        return manager;
    }


    @Bean
    UserRealm userRealm(EhCacheManager cacheManager, HashedCredentialsMatcher hashedCredentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(cacheManager);
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

    /**
     * 加密策略
     *
     * @return
     */
    @Bean
    HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }


    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }


    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/check");
        shiroFilterFactoryBean.setSuccessUrl("/**");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String, String> chains = Maps.newLinkedHashMap();
        String anonUrls = env.getProperty("shiro.anon.urls");
        String[] anonArray = anonUrls.split(",");
        for (String anonUrl : anonArray) {
            chains.put(anonUrl, "anon");
        }
        chains.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chains);


        return shiroFilterFactoryBean;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
