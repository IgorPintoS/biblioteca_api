package com.example.biblioteca.services;

import com.example.biblioteca.exceptions.BookListIsEmptyException;
import com.example.biblioteca.exceptions.BookNameAndPublisherException;
import com.example.biblioteca.exceptions.BookNameOrAuthorIsEmptyException;
import com.example.biblioteca.exceptions.BookNotFoundException;
import com.example.biblioteca.models.Book;
import com.example.biblioteca.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book newBook) {
        if(newBook.getName().isEmpty() || newBook.getAuthor().isEmpty()) {
            throw new BookNameOrAuthorIsEmptyException("Book name or author is empty.");
        }

        boolean exists = bookRepository.findByNameAndPublisher(newBook.getName(), newBook.getAuthor());

        if(exists) {
            throw new BookNameAndPublisherException("Book name: " + newBook.getName() +
                    " and publisher: " + newBook.getAuthor() +  " already exists.");
        }

        return bookRepository.save(newBook);
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();

        if(bookList.isEmpty()) {
            throw new BookListIsEmptyException("Book list is empty.");
        }

        return bookList;
    }

    public Book getBook(UUID id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if(bookOptional.isEmpty()) {
            throw new BookNotFoundException("Book not found.");
        }

        return bookOptional.get();
    }

    public Book updateBook(UUID id, Book bookUpdate) {
        Book book = this.getBook(id);

        if(bookUpdate.getName().isEmpty() || bookUpdate.getAuthor().isEmpty()) {
            throw new BookNameOrAuthorIsEmptyException("Book name or author is empty.");
        }

        boolean exists = bookRepository.findByNameAndPublisher(bookUpdate.getName(), bookUpdate.getPublisher());

        if(exists) {
            throw new BookNameAndPublisherException("Book name: " + bookUpdate.getName() +
                    " and publisher: " + bookUpdate.getPublisher() +  " already exists.");
        }

        book.setName(bookUpdate.getName());
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
