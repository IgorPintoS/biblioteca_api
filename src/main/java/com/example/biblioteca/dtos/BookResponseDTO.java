package com.example.biblioteca.dtos;

import java.util.UUID;

public record BookResponseDTO(
                                UUID idBook,
                                String name,
                                String genre,
                                String author,
                                String publisher) {
}
