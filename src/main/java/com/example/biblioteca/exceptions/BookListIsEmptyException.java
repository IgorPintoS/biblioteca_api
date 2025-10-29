package com.example.biblioteca.exceptions;

public class BookListIsEmptyException extends RuntimeException {
    public BookListIsEmptyException(String message) {
        super(message);
    }
}
