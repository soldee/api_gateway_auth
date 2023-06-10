package com.soldee.api_gateway_auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soldee.api_gateway_auth.authorization.AuthorizationService;
import com.soldee.api_gateway_auth.authorization.DBAuthorizationService;
import com.soldee.api_gateway_auth.authorization.InMemoryAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class AuthorizationConfigurer {

    Logger log = LoggerFactory.getLogger(AuthorizationConfigurer.class);
    ResourceLoader resourceLoader;
    private ObjectMapper objectMapper;


    public AuthorizationConfigurer(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.resourceLoader = resourceLoader;
    }


    private InputStream getConfigFile() {
        try {
            return resourceLoader.getResource("").getInputStream();
        } catch (IOException e) {
            log.error("Failed to read config file");
            throw new RuntimeException(e);
        }
    }

    private ConfigFileDto parseConfigFile() {
        try {
            return objectMapper.readValue(getConfigFile(), ConfigFileDto.class);
        } catch (IOException e) {
            log.error("Failed to parse config file");
            throw new RuntimeException(e);
        }
    }


    @Bean
    public AuthorizationService authorizationService() {
        ConfigFileDto configFile = parseConfigFile();

        if (configFile.configFileAuthDto.inMemory) {
            return new InMemoryAuthorizationService(configFile.configFileAuthDto.roles);
        }
        return new DBAuthorizationService();
    }
}
