package com.soldee.api_gateway_auth.authorization;

import com.soldee.api_gateway_auth.config.ConfigFileDto;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Service;

import java.util.Optional;



public class DBAuthorizationService implements AuthorizationService {

    private final ConfigFileDto configurationFile;

    public DBAuthorizationService(ConfigFileDto configurationFile) {
        this.configurationFile = configurationFile;
    }

    @Override
    public Optional<ClientDto> getClient(String name) {
        return Optional.empty();
    }

}
