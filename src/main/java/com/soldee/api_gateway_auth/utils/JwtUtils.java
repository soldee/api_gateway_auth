package com.soldee.api_gateway_auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtUtils {

    private int JWT_EXP_SECONDS;
    private String JWT_SECRET;
    private Algorithm algorithm;

    public JwtUtils() {
        initClaims();
    }

    private void initClaims() {
        JWT_EXP_SECONDS = 60 * 60;
        JWT_SECRET = "testing secret";
        algorithm = Algorithm.HMAC256(JWT_SECRET);
    }

    public String generateToken(String subject, String[] roles) {
        return JWT.create()
                .withIssuer("API-AUTH")
                .withSubject(subject)
                .withArrayClaim("roles", roles)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L*JWT_EXP_SECONDS ))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date())
                .sign(algorithm);
    }

    public void refreshClaims() {
        initClaims();
    }
}
