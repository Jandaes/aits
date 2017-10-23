package com.liu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author jared
 */
@Configuration
//设置扫描路径
@EnableJpaRepositories("com.liu.dao")
public class JpaConfiguration {
}
