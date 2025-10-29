package com.example.biblioteca.dtos;

import com.example.biblioteca.enums.BookGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookCreateDTO(@NotBlank @Size(max = 60) String name,
                            @NotBlank BookGenre genre,
                            @NotBlank @Size(max = 60) String author,
                            @Size(max = 60) String publisher) {
}
