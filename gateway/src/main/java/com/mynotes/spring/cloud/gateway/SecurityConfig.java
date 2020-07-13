package com.mynotes.spring.cloud.gateway;

import static org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest.toAnyEndpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
            .matchers(toAnyEndpoint()).permitAll()
            .anyExchange().permitAll()
            .and()
            .httpBasic()
            .and()
            .headers().disable()
            .csrf().disable()
            .formLogin().disable()
            .logout().disable()
            .build();
    }

}
