package com.soldee.api_gateway_auth.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.soldee.api_gateway_auth.authorization.ClientDto;
import java.util.List;


@JsonDeserialize
public class ConfigFileAuthDto {

    @JsonProperty("in_memory")
    boolean inMemory;
    @JsonProperty
    List<ClientDto> roles;

    public List<ClientDto> getRoles() {
        return roles;
    }

    public boolean isInMemory() {
        return inMemory;
    }
}
