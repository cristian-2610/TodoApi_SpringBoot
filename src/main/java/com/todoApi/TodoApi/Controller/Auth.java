package com.todoApi.TodoApi.Controller;

import com.todoApi.TodoApi.Service.UserService;
import com.todoApi.TodoApi.Utils.Dto.AuthReq;
import com.todoApi.TodoApi.Utils.Dto.AuthResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth/")
@RequiredArgsConstructor
public class Auth {

    private final UserService userService;

    @PostMapping("authentication")
    public ResponseEntity<AuthResp> authentication(@RequestBody() AuthReq authReq) {
        return new ResponseEntity<>(userService.authentication(authReq), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<AuthResp> register(@RequestBody() AuthReq authReq) {
        return new ResponseEntity<>(userService.register(authReq), HttpStatus.OK);
    }


}
