package com.aits;

import com.aits.config.RedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author jared
 */
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
@EnableCaching
@EnableScheduling
//开启servlet注解
@ServletComponentScan
public class JaredMain {

    public static void main(String[] args) {
        SpringApplication.run(JaredMain.class, args);
    }

}