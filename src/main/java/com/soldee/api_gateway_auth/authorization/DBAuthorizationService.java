package com.soldee.api_gateway_auth.authorization;

import com.soldee.api_gateway_auth.authorization.repository.ClientDto;
import com.soldee.api_gateway_auth.authorization.repository.ClientRepository;
import com.soldee.api_gateway_auth.authorization.repository.RoleDto;
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
        List<ClientDto> clients = clientRepository.findClientWithRolesByName(name);

        List<String> roles = clients.stream()
                //.map(ClientDto::getRoles)
                .flatMap(clientDto -> clientDto.getRoles().stream())
                .map(roleDto -> roleDto.getRole())
                .collect(Collectors.toList());

        Client client = new Client();
        client.setRoles(roles);
        client.setName(clients.get(0).getName());

        return Optional.of(client);
    }

}
