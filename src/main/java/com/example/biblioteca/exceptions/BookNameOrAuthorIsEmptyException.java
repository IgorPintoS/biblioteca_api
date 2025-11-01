package com.example.biblioteca.exceptions;

public class BookNameOrAuthorIsEmptyException extends RuntimeException {
    public BookNameOrAuthorIsEmptyException(String message) {
        super(message);
    }
}
