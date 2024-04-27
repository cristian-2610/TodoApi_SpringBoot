package com.todoApi.TodoApi.Utils.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    int id;
    @NotNull(message = "REQUIRED FIELD.")
    String title;
    @NotNull(message = "REQUIRED FIELD.")
    String description;
    @NotNull(message = "REQUIRED FIELD.")
    String author;
}
