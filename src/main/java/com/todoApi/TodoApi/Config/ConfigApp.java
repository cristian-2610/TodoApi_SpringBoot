package com.todoApi.TodoApi.Config;


import com.todoApi.TodoApi.Repository.UserRepository;
import com.todoApi.TodoApi.Utils.Dto.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ConfigApp {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow((() -> new ExceptionHandler(HttpStatus.NOT_FOUND, "INFORMATION NOT FOUND.")));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(PasswordEncoder());
        auth.setUserDetailsService(userDetailsService());
        return auth;
    }


    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration auht) throws Exception {
        return auht.getAuthenticationManager();
    }
}
