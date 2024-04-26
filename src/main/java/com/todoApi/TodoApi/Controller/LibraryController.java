package com.todoApi.TodoApi.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/library/")
public class LibraryController {

    @GetMapping("")
    public ResponseEntity Books() {
        return new ResponseEntity<>("Pinocho", HttpStatus.OK);
    }
}
