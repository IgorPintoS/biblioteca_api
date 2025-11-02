package com.example.biblioteca.exceptions;

public class BookTitleAndPublisherExistsException extends RuntimeException {
    public BookTitleAndPublisherExistsException(String message) {
        super(message);
    }
}
