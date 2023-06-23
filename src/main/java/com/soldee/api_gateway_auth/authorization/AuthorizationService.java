package com.soldee.api_gateway_auth.authorization;

import com.soldee.api_gateway_auth.config.Client;

import java.util.Optional;

public interface AuthorizationService {

    public Optional<Client> getClient(String name);

}
