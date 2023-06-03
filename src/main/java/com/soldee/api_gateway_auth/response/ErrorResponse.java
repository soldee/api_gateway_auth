package com.soldee.api_gateway_auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class ErrorResponse {

    @JsonProperty("error")
    private final String error;
    @JsonProperty("url")
    private final String url;

    public ErrorResponse(Exception error, String url) {
        this.error = error.getLocalizedMessage();
        this.url = url;
    }
}
