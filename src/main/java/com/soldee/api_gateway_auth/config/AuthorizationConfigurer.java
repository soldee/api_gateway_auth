package com.soldee.api_gateway_auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soldee.api_gateway_auth.authorization.AuthorizationService;
import com.soldee.api_gateway_auth.authorization.DBAuthorizationService;
import com.soldee.api_gateway_auth.authorization.InMemoryAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class AuthorizationConfigurer {

    Logger log = LoggerFactory.getLogger(AuthorizationConfigurer.class);
    ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;


    public AuthorizationConfigurer(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.resourceLoader = resourceLoader;
    }


    private InputStream getConfigFile() {
        try {
            return resourceLoader.getResource("classpath:auth_config.json").getInputStream();
        } catch (IOException e) {
            log.error("Failed to read config file");
            throw new RuntimeException(e);
        }
    }

    @Bean
    public ConfigFileDto configurationFile() {
        try {
            return objectMapper.readValue(getConfigFile(), ConfigFileDto.class);
        } catch (IOException e) {
            log.error("Failed to parse config file");
            throw new RuntimeException(e);
        }
    }

    @Bean
    public AuthorizationService authorizationService() {
        ConfigFileDto configFile = configurationFile();
        if (configFile.getConfigFileAuthDto().isInMemory()) {
            InMemoryAuthorizationService authService = new InMemoryAuthorizationService(configFile);
            authService.refreshClients();
            return authService;
        }
        return new DBAuthorizationService(configFile);
    }

}
