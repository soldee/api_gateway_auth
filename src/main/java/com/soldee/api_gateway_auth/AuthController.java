package com.soldee.api_gateway_auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    public AuthController() {
    }

    @GetMapping
    public String Hello(){
        return "hello";
    }

}
