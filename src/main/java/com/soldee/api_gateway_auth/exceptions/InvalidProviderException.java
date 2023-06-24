package com.soldee.api_gateway_auth.exceptions;

public class InvalidProviderException extends ApiException {

    public InvalidProviderException() {
        super("Invalid provider in certificate");
    }
}
