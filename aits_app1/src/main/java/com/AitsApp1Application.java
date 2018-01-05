package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
//@EnableFeignClients
//@EnableEurekaClient
//@EnableDiscoveryClient
public class AitsApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(AitsApp1Application.class, args);
	}
}
