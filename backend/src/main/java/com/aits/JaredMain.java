package com.aits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author jared
 */
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
@EnableCaching
public class JaredMain {

    public static void main(String[] args) {
        SpringApplication.run(JaredMain.class, args);
    }
}