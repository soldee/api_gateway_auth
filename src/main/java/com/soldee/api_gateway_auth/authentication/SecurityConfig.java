package com.soldee.api_gateway_auth.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import java.security.cert.CertificateParsingException;


@EnableWebSecurity
@Component
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(matcherRegistry -> {
                    matcherRegistry
                            .anyRequest().authenticated();
                })
                .x509(x509Configurer -> {
                    x509Configurer
                            .x509PrincipalExtractor(cert -> {
                                System.out.println(cert.getIssuerX500Principal());
                                System.out.println(cert.getSubjectDN().getName());
                                System.out.println(cert.getSubjectX500Principal().getName());
                                System.out.println(cert.getSerialNumber());
                                try {
                                    cert.getSubjectAlternativeNames().forEach(System.out::println);
                                } catch (CertificateParsingException e) {
                                    e.printStackTrace();
                                }
                                return "test";
                            })
                            .userDetailsService(username -> {
                                if (username.equals("test")) {
                                    return new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
                                }
                                throw new UsernameNotFoundException("Invalid certificate");
                            });
                })
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfigurer -> {
                    sessionConfigurer
                            .sessionCreationPolicy(SessionCreationPolicy.NEVER);
                })
                .build();
    }

}
