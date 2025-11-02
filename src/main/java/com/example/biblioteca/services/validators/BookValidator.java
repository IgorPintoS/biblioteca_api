package com.example.biblioteca.services.validators;

import com.example.biblioteca.exceptions.BookNotFoundException;
import com.example.biblioteca.exceptions.BookTitleAndPublisherExistsException;
import com.example.biblioteca.exceptions.ListIsEmptyException;
import com.example.biblioteca.models.Book;
import com.example.biblioteca.repositories.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookValidator {

    private final BookRepository bookRepository;

    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void validateFindBookByTitleAndPublisher(String title, String publisher) {
        boolean exists = bookRepository.findByTitleAndPublisher(title, publisher);

        if(exists) {
            throw new BookTitleAndPublisherExistsException("Book title: " + title +
                    " and publisher: " + publisher +  " already exists.");
        }
    }

    public void validateListIsEmpty(List<?> list) {
        if(list.isEmpty()) {
            throw new ListIsEmptyException("List of books is empty.");
        }
    }

    public Book validateBookNotFound(UUID id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }

        return bookOptional.get();
    }
}
