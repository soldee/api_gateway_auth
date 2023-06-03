package com.soldee.api_gateway_auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize
public class JwtResponse {

    @JsonProperty("jwt")
    private final String jwt;

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }
}
