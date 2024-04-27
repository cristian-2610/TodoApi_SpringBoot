package com.todoApi.TodoApi.Controller;

import com.todoApi.TodoApi.Utils.Dto.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllAdvices {

    @org.springframework.web.bind.annotation.ExceptionHandler(ExceptionHandler.class)
    public ResponseEntity ExceptionResponse(ExceptionHandler ex) {
        Map<String, String> message = new HashMap<>();
        message.put("message", ex.getMessage());
        return new ResponseEntity<>(message, ex.getStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ResponseEntity UserDetails(AuthenticationException ex) {
        Map<String, String> message = new HashMap<>();
        message.put("message", ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);
    }
}
