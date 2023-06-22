package com.soldee.api_gateway_auth.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@Data
public class ConfigFileDto {

    ConfigFileAuthDto auth;
    ConfigFileJwtDto jwt;

}