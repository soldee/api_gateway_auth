package com.soldee.api_gateway_auth.config;

import com.soldee.api_gateway_auth.authorization.ClientDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("auth")
@Data
public class ConfigFileAuthDto {

    boolean inMemory;
    List<ClientDto> users;
}
