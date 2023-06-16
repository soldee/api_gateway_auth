package com.soldee.api_gateway_auth.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigFileJwtDto {

    @JsonProperty("contains_role")
    boolean containsRole;

    public boolean isContainsRole() {
        return containsRole;
    }
}
