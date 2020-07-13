package com.mynotes.spring.cloud.gateway;

import static org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest.to;
import static org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest.toAnyEndpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${management.security.roles:ADMIN}")
    String roles;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
//            .matchers(to(HealthEndpoint.class)).permitAll()
//            .matchers(toAnyEndpoint()).hasRole(roles)
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

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
            UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles(roles)
                .build();
            return new MapReactiveUserDetailsService(userDetails);
    }

}
