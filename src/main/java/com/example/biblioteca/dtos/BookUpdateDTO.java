package com.example.biblioteca.dtos;

import java.util.UUID;

//DTO - transporte de dados entre componetes, agrupa um conjunto de atributos em uma classe simples.
public record BookUpdateDTO(String name, String genre, String author, String publisher) {
}
