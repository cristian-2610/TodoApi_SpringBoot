package com.todoApi.TodoApi.Jwt;


import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = "";


    }

    private String getToken(HttpServletRequest request) {
        existToken(request);
        return request.getHeader("Authentication").replace("Bearer ", "");
    }

    private void existToken(HttpServletRequest token) {
        if (!token.getHeader("Authentication").startsWith("Bearer")) System.out.println("Error");
        System.out.println("Ok");
    }

}
