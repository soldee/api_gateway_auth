package com.soldee.api_gateway_auth.authorization;
import lombok.Data;

import java.util.List;

@Data
public class ClientDto {

    private String name;
    private List<String> roles;

}
