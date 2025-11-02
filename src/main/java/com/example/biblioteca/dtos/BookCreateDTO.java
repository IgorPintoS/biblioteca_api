package com.example.biblioteca.dtos;

import com.example.biblioteca.enums.BookGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookCreateDTO(@NotBlank(message = "Book name cannot be empty.") @Size(max = 60) String title,
                            @NotBlank BookGenre genre,
                            @NotBlank(message = "Author name cannot be empty.") @Size(max = 60) String author,
                            @Size(max = 60) String publisher) {
}
