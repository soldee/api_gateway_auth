package com.soldee.api_gateway_auth.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.X509Configurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@EnableWebSecurity
@Component
public class SecurityConfig {

    Logger log = LoggerFactory.getLogger(SecurityConfig.class);


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(matcherRegistry -> {
                    matcherRegistry
                            .anyRequest().authenticated();
                })
                .x509(this::x509Config)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfigurer -> {
                    sessionConfigurer
                            .sessionCreationPolicy(SessionCreationPolicy.NEVER);
                })
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }


    private void x509Config(X509Configurer<HttpSecurity> x509Configurer) {
        x509Configurer
                .x509PrincipalExtractor(this::extractCN)
                .userDetailsService(username -> new User(username, "", AuthorityUtils.NO_AUTHORITIES));
    }


    private String extractCN(X509Certificate x509Cert) {
        String certificateInfo = x509Cert.getSubjectDN().getName();

        log.debug("Extracting certificate info..." + certificateInfo);

        Pattern subjectCNPattern = Pattern.compile("CN=(.*?)(?:, )", Pattern.CASE_INSENSITIVE);
        Matcher matcher = subjectCNPattern.matcher(certificateInfo);

        if (!matcher.find()) throw new UsernameNotFoundException("Invalid certificate");

        try {
            String cn = matcher.group(1);
            log.info("Extracted CN=" + cn);

            return cn;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid certificate");
        }
    }


    private User authenticateUser(String username) {
        log.debug("Authenticating user: " + username);

        if (username.equals("bob")) {
            return new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        }
        throw new UsernameNotFoundException("Invalid certificate");
    }

}
