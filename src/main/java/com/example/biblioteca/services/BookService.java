package com.example.biblioteca.services;

import com.example.biblioteca.exceptions.BookTitleAndPublisherExistsException;
import com.example.biblioteca.models.Book;
import com.example.biblioteca.repositories.BookRepository;
import com.example.biblioteca.services.validators.BookValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
Responsável pelo contato com o repository e regras de negócio.
 */

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookValidator bookValidator;

    public BookService(BookRepository bookRepository, BookValidator bookValidator) {
        this.bookRepository = bookRepository;
        this.bookValidator = bookValidator;
    }

    public Book createBook(Book newBook) {
        bookValidator.validateFindBookByTitleAndPublisher(newBook.getTitle(), newBook.getPublisher());

        return bookRepository.save(newBook);
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        bookValidator.validateListIsEmpty(bookList);

        return bookList;
    }

    public Book getBook(UUID id) {

        return bookValidator.validateBookNotFound(id);
    }

    public Book updateBook(UUID id, Book bookUpdate) {
        Book book = this.getBook(id);
        bookValidator.validateFindBookByTitleAndPublisher(bookUpdate.getTitle(), bookUpdate.getPublisher());

        book.setTitle(bookUpdate.getTitle());
        book.setAuthor(bookUpdate.getAuthor());
        book.setGenre(bookUpdate.getGenre());
        book.setPublisher(bookUpdate.getPublisher());

        return bookRepository.save(book);
    }

    public void deleteBook(UUID id) {
        Book book = this.getBook(id);

        bookRepository.delete(book);
    }
}
