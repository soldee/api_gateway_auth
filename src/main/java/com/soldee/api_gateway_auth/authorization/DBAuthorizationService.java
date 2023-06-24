package com.soldee.api_gateway_auth.authorization;

import com.soldee.api_gateway_auth.authorization.repository.ClientRepository;
import com.soldee.api_gateway_auth.config.Client;
import com.soldee.api_gateway_auth.config.ConfigFileDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@ConditionalOnMissingBean(InMemoryAuthorizationService.class)
public class DBAuthorizationService implements AuthorizationService {

    private final ConfigFileDto configurationFile;
    private final ClientRepository clientRepository;

    public DBAuthorizationService(ConfigFileDto configurationFile, ClientRepository clientRepository) {
        this.configurationFile = configurationFile;
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> getClient(String name) {

        Set<ClientRepository.ClientResult> clients = clientRepository.findClientWithRolesByName(name);

        if (clients.isEmpty()) return Optional.empty();

        return Optional.of(
                new Client(
                        clients.iterator().next().getName(),
                        clients.stream().map(ClientRepository.ClientResult::getRole).collect(Collectors.toSet())
                )
        );
    }

}
