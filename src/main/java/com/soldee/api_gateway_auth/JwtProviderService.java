package com.soldee.api_gateway_auth;

import com.soldee.api_gateway_auth.authorization.AuthorizationService;
import com.soldee.api_gateway_auth.authorization.ClientDto;
import com.soldee.api_gateway_auth.exceptions.EmptyRolesException;
import com.soldee.api_gateway_auth.exceptions.InvalidProviderException;
import com.soldee.api_gateway_auth.utils.JwtUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtProviderService {

    private final AuthorizationService authorizationService;
    private final JwtUtils jwtUtils;

    public JwtProviderService(AuthorizationService authorizationService, JwtUtils jwtUtils) {
        this.authorizationService = authorizationService;
        this.jwtUtils = jwtUtils;
    }

    public String provideJwt(String clientName) throws InvalidProviderException, EmptyRolesException {

        ClientDto client = authorizationService.containsClient(clientName)
                .orElseThrow(InvalidProviderException::new);

        if (client.getRoles().isEmpty()) throw new EmptyRolesException();

        return jwtUtils.generateToken(
                client.getName(),
                client.getRoles().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new)
        );
    }
}
