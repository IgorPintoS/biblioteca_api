package com.example.biblioteca.dtos;

import jakarta.validation.constraints.NotBlank;
//DTO - transporte de dados entre componetes, agrupa um conjunto de atributos em uma classe simples.
public record BookRecordDto(
        @NotBlank //anotações de validação
        String name,
        @NotBlank
        String genre,
        @NotBlank
        String author,
        @NotBlank
        String publisher

) {
}
