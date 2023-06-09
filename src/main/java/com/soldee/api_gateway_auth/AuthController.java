package com.soldee.api_gateway_auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController() {
    }

    @GetMapping
    public String Hello(Principal principal){
        log.info("PRINCIPAL: " + principal.getName());
        return "hello";
    }

}
