package com.soldee.api_gateway_auth;
import com.soldee.api_gateway_auth.response.JwtResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/v1")
public class JwtController {

    private final JwtProviderService jwtProviderService;

    public JwtController(JwtProviderService jwtProviderService) {
        this.jwtProviderService = jwtProviderService;
    }

    @GetMapping(
            value = "/auth",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JwtResponse> generateJwt(Principal principal) throws Exception {
        return ResponseEntity.ok(
                new JwtResponse(jwtProviderService.provideJwt(principal.getName()))
        );
    }
}
