package com.soldee.api_gateway_auth.authorization;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.username:root}")
    String username;

    @Value("${spring.datasource.username:root}")
    String password;


    @Bean
    @ConditionalOnBean(DBAuthorizationService.class)
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/api_gateway")
                .username(username)
                .password(password)
                .build();
    }
}
