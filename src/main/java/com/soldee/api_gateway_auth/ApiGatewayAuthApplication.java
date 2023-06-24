package com.soldee.api_gateway_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApiGatewayAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayAuthApplication.class, args);
    }

}
