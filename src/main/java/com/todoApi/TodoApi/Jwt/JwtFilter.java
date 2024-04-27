package com.todoApi.TodoApi.Jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = extractHeader(request);
        String username;
        String token;

        if (header == null) {

            filterChain.doFilter(request, response);
            return;
        }
        token = extractToken(header);
        username = extracSubjet(token);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println(username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isValidToken(userDetails, token)) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }


    }

    private String extracSubjet(String token) {
        return jwtService.getSubjet(token);
    }

    private String extractHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return token;
    }

    private String extractToken(String token) {
        return token.replace("Bearer ", "");
    }

}
