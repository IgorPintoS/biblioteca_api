package com.example.biblioteca.exceptions;

public class ListIsEmptyException extends RuntimeException {
    public ListIsEmptyException(String message) {
        super(message);
    }
}
