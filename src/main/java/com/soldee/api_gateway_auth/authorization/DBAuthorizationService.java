package com.soldee.api_gateway_auth.authorization;

import com.soldee.api_gateway_auth.config.ConfigFileDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@ConditionalOnMissingBean(InMemoryAuthorizationService.class)
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
