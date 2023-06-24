package com.soldee.api_gateway_auth.exceptions;

public class EmptyRolesException extends ApiException {

    public EmptyRolesException() {
        super("Provider has no valid roles assigned");
    }
}
