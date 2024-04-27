package com.todoApi.TodoApi.Service;


import com.todoApi.TodoApi.Jwt.JwtService;
import com.todoApi.TodoApi.Model.User;
import com.todoApi.TodoApi.Repository.UserRepository;
import com.todoApi.TodoApi.Utils.Dto.AuthReq;
import com.todoApi.TodoApi.Utils.Dto.AuthResp;
import com.todoApi.TodoApi.Utils.Dto.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager auth;
    private final JwtService jwtService;

    public AuthResp authentication(AuthReq authReq) {

        auth.authenticate(new UsernamePasswordAuthenticationToken(
                authReq.getEmail(),
                authReq.getPassword()
        ));

        return AuthResp.builder()
                .jwt(jwtService.generateToken
                        (authReq.getEmail())
                )
                .build();

    }

    public AuthResp register(AuthReq authReq) {
        if (repository.findByEmail(authReq.getEmail()).isPresent()) {
            throw new ExceptionHandler(HttpStatus.MULTI_STATUS, "Email in Used.");
        }

        repository.save(
                User.builder()
                        .email(authReq.getEmail())
                        .rol("ADMIN")
                        .password(encoder.encode(authReq.getPassword()))
                        .build()
        );

        return AuthResp.builder()
                .jwt(
                        jwtService.generateToken(authReq.getEmail())
                )
                .build();
    }

    private String getUsernameContexHolder() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

}
