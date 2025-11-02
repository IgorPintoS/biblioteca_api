package com.example.biblioteca.dtos;

import com.example.biblioteca.enums.BookGenre;

import java.util.UUID;

public record BookResponseDTO(UUID idBook,
                              String title,
                              BookGenre genre,
                              String author,
                              String publisher) {
}
