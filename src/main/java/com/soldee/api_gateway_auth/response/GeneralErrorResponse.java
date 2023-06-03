package com.soldee.api_gateway_auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class GeneralErrorResponse {

    @JsonProperty("url")
    private final String url;

    public GeneralErrorResponse(String url) {
        this.url = url;
    }
}
