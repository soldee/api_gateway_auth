package com.soldee.api_gateway_auth.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class ConfigFileDto {

    @JsonProperty("auth")
    ConfigFileAuthDto configFileAuthDto;
    @JsonProperty("jwt")
    ConfigFileJwtDto configFileJwtDto;

}