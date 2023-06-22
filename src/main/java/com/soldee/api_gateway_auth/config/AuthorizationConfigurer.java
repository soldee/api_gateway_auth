package com.soldee.api_gateway_auth.config;

import com.soldee.api_gateway_auth.authorization.AuthorizationService;
import com.soldee.api_gateway_auth.authorization.DBAuthorizationService;
import com.soldee.api_gateway_auth.authorization.InMemoryAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class AuthorizationConfigurer {

    Logger log = LoggerFactory.getLogger(AuthorizationConfigurer.class);
    private final ConfigFileDto configFile;


    public AuthorizationConfigurer(ConfigFileDto configFile) {
        this.configFile = configFile;
    }


    /*@Bean
    public AuthorizationService authorizationService() {
        System.out.println("AAAAAAAAAAAAAAAAAAAABBB - " + configFile.getConfigFileAuthDto());
        if (configFile.getConfigFileAuthDto().isInMemory()) {
            InMemoryAuthorizationService authService = new InMemoryAuthorizationService(configFile);
            authService.refreshClients();
            return authService;
        }
        return new DBAuthorizationService(configFile);
    }*/

}
