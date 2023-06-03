package com.soldee.api_gateway_auth.authorization;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;


public class ClientDto {

    private final int id;
    private final String name;
    private List<GrantedAuthority> roles;

    public ClientDto(int id, String name, List<GrantedAuthority> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public List<GrantedAuthority> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setRoles(List<GrantedAuthority> roles) {
        this.roles = roles;
    }
}
