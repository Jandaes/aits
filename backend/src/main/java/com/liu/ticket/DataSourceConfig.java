package com.liu.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    // 从properties中读取参数
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.pwd}")
    private String pwd;

//    @Value("${srping.datasource.initialSize}")
//    private Integer initialSize;
//    @Value("${spring.datasource.maxActive}")
//    private Integer maxActive;


    // system data source
    @Bean
    @Primary
    public DataSource dataSource() throws Exception {
        return tomcatPoolingDataSource();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
        return new JdbcTemplate(dataSource());
    }

    private DataSource tomcatPoolingDataSource() throws Exception {
        final org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(pwd);

     //   dataSource.setInitialSize(initialSize); // 连接池启动时创建的初始化连接数量（默认值为0）
     //   dataSource.setMaxActive(maxActive); // 连接池中可同时连接的最大的连接数
        dataSource.setMaxIdle(5); // 连接池中最大的空闲的连接数，超过的空闲连接将被释放，如果设置为负数表示不限
        dataSource.setMinIdle(0); // 连接池中最小的空闲的连接数，低于这个数量会被创建新的连接
        dataSource.setMaxWait(60000); // 最大等待时间，当没有可用连接时，连接池等待连接释放的最大时间，超过该时间限制会抛出异常，如果设置-1表示无限等待
        dataSource.setRemoveAbandonedTimeout(180); // 超过时间限制，回收没有用(废弃)的连接
        dataSource.setRemoveAbandoned(true); // 超过removeAbandonedTimeout时间后，是否进 行没用连接（废弃）的回收
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30); // 检查无效连接的时间间隔 设为30分钟
        return dataSource;
    }
}
