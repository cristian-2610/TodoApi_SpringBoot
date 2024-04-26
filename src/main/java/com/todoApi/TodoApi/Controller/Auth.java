package com.todoApi.TodoApi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth/")
public class Auth {

    @GetMapping("authentication")
    public ResponseEntity authentication() {
        return new ResponseEntity<>("Hola authentication --", HttpStatus.OK);
    }
}
