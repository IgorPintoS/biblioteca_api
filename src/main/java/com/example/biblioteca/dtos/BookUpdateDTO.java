package com.example.biblioteca.dtos;

import com.example.biblioteca.enums.BookGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

//DTO - transporte de dados entre componetes, agrupa um conjunto de atributos em uma classe simples.
public record BookUpdateDTO(@NotBlank(message = "Book name cannot be empty.") @Size(max = 60) String title,
                            @NotBlank BookGenre genre,
                            @NotBlank(message = "Author name cannot be empty.") @Size(max = 60) String author,
                            @Size(max = 60) String publisher) {
}
