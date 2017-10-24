package com.aits.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author jared
 */
@Configuration
//设置扫描路径
@EnableJpaRepositories("com.aits.dao")
public class JpaConfiguration {
}
