package com.soldee.api_gateway_auth.authorization;

import java.util.Optional;

public interface AuthorizationService {

    public Optional<ClientDto> getClient(String name);

}
