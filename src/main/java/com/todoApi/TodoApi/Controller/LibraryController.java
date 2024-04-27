package com.todoApi.TodoApi.Controller;


import com.todoApi.TodoApi.Service.BookService;
import com.todoApi.TodoApi.Utils.Dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("api/v1/library/")
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity allBooks() {
        return new ResponseEntity<>(Map.of("Books", bookService.allBook()), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveBooks(@RequestBody() BookDTO bookDTO) {
        bookService.save(bookDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity saveBooks(@RequestBody() BookDTO bookDTO, @PathVariable("id") int id) {
        bookService.updateBook(bookDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBooks(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
