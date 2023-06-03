package com.soldee.api_gateway_auth.authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AuthorizationService {

    private List<ClientDto> clients;
    private List<GrantedAuthority> roles;
    Logger log = LoggerFactory.getLogger(AuthorizationService.class);

    public AuthorizationService() {
        initRoles();
        initClients();
    }

    private void initClients() {
        List<ClientDto> fetchedClients = Arrays.asList(
                new ClientDto(1, "APP1", Arrays.asList(new SimpleGrantedAuthority("insert_user"))),
                new ClientDto(2, "APP2", Arrays.asList(new SimpleGrantedAuthority("admin"))),
                new ClientDto(3, "APP3", Arrays.asList(new SimpleGrantedAuthority("insert_user"), new SimpleGrantedAuthority("read_user"))),
                new ClientDto(4, "APP4", Arrays.asList(new SimpleGrantedAuthority("invalid user role")))
        );

        this.clients = fetchedClients.stream().filter(clientDto -> {
            clientDto.setRoles(
                    clientDto.getRoles().stream()
                            .filter(role -> {
                                System.out.println(role);
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
        this.roles = Arrays.asList(
                new SimpleGrantedAuthority("admin"),
                new SimpleGrantedAuthority("read_user"),
                new SimpleGrantedAuthority("insert_user")
        );
    }

    public void refreshClients() {
        initRoles();
        initClients();
    }

    public Optional<ClientDto> containsClient(String name) {
        return clients.stream()
                .filter(clientDto -> clientDto.getName().equals(name))
                .findFirst();
    }
}
