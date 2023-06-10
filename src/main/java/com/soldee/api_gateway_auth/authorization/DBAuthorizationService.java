package com.soldee.api_gateway_auth.authorization;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBAuthorizationService implements AuthorizationService {
    @Override
    public Optional<ClientDto> getClient(String name) {
        return Optional.empty();
    }
}
