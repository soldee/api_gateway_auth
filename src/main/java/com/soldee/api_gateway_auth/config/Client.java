package com.soldee.api_gateway_auth.config;
import lombok.Data;

import java.util.List;

@Data
public class Client {

    private String name;
    private List<String> roles;

}
