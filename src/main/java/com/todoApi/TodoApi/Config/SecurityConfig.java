package com.todoApi.TodoApi.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
                authorizeHttpRequests(auth ->
                        auth.antMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated())
                .formLogin();


        return http.build();
    }
}
