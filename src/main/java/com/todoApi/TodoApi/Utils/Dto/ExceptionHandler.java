package com.todoApi.TodoApi.Utils.Dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionHandler extends RuntimeException {
    private HttpStatus status;

    public ExceptionHandler(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
