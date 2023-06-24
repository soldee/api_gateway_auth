package com.soldee.api_gateway_auth.config;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private String name;
    private Set<String> roles;

}
