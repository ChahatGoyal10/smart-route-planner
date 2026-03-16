package com.routeplanner.smartrouteplanner.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/",
                "/index.html",
                "/auth/**",
                "/roads/**",
                "/bucket/**",
                "/reviews/**",
                "/contact/**",
                "/favourites/**",
                "/css/**",
                "/js/**",
                "/favicon.ico",
                "/images/**"
            ).permitAll()
            .anyRequest().authenticated()
        );

    return http.build();
}
}