package com.soldee.api_gateway_auth.authorization;
import com.soldee.api_gateway_auth.config.Client;
import com.soldee.api_gateway_auth.config.ConfigFileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@ConditionalOnProperty(prefix = "auth", name = "in-memory", havingValue = "true")
public class InMemoryAuthorizationService implements AuthorizationService {

    private List<Client> clients;
    Logger log = LoggerFactory.getLogger(InMemoryAuthorizationService.class);

    private final ConfigFileDto configurationFile;

    public InMemoryAuthorizationService(ConfigFileDto configurationFile) {
        this.configurationFile = configurationFile;
        refreshClients();
    }


    private void initClients() {
        this.clients = configurationFile.getAuth().getUsers();
    }

    public void refreshClients() {
        initClients();
    }

    @Override
    public Optional<Client> getClient(String name) {
        return clients.stream()
                .filter(clientDto -> clientDto.getName().equals(name))
                .findFirst();
    }
}
