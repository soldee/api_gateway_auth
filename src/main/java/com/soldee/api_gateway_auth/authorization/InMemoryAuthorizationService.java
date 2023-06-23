package com.soldee.api_gateway_auth.authorization;
import com.soldee.api_gateway_auth.config.Client;
import com.soldee.api_gateway_auth.config.ConfigFileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@ConditionalOnProperty(prefix = "auth", name = "in-memory", havingValue = "true")
public class InMemoryAuthorizationService implements AuthorizationService {

    private List<Client> clients;
    private List<String> roles;
    Logger log = LoggerFactory.getLogger(InMemoryAuthorizationService.class);

    private final ConfigFileDto configurationFile;

    public InMemoryAuthorizationService(ConfigFileDto configurationFile) {
        this.configurationFile = configurationFile;
        refreshClients();
    }


    private void initClients() {
        List<Client> fetchedClients = configurationFile.getAuth().getUsers();

        this.clients = fetchedClients.stream().filter(clientDto -> {
            clientDto.setRoles(
                    clientDto.getRoles().stream()
                            .filter(role -> {
                                boolean isValidRole = roles.contains(role);
                                if (!isValidRole) log.warn("Invalid role \"" + role + "\"");
                                return isValidRole;
                            })
                            .collect(Collectors.toList())
            );

            boolean hasEmptyRoles = clientDto.getRoles().isEmpty();
            if (hasEmptyRoles) log.error("\"" + clientDto.getName() + "\" has no valid roles");
            return !hasEmptyRoles;

        }).collect(Collectors.toList());
    }


    private void initRoles() {
        this.roles = Arrays.asList("ADMIN","READ","INSERT");
    }

    public void refreshClients() {
        initRoles();
        initClients();
    }

    @Override
    public Optional<Client> getClient(String name) {
        return clients.stream()
                .filter(clientDto -> clientDto.getName().equals(name))
                .findFirst();
    }
}
