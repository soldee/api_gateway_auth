package com.soldee.api_gateway_auth.authorization;
import java.util.List;


public class ClientDto {

    private final int id;
    private final String name;
    private List<String> roles;

    public ClientDto(int id, String name, List<String> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
