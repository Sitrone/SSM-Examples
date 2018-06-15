package com.sununiq.scaffold.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @program: springboot-mybatis-scaffold
 *
 * @description: 数据源配置
 *
 * @author: sununiq
 *
 * @create: 2018-06-11 22:45
 **/
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	public DataSource dataSource() {
		return new DruidDataSource();
	}
}
