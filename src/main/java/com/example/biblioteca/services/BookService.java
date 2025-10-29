package com.example.biblioteca.services;

import com.example.biblioteca.dtos.BookCreateDTO;
import com.example.biblioteca.dtos.BookResponseDTO;
import com.example.biblioteca.exceptions.BookListIsEmptyException;
import com.example.biblioteca.exceptions.BookNameAndPublisherException;
import com.example.biblioteca.mapper.BookMapper;
import com.example.biblioteca.models.Book;
import com.example.biblioteca.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookResponseDTO createBook(BookCreateDTO bookCreateDTO) {
        Book book = bookMapper.createBookFromDTO(bookCreateDTO);
        boolean exists = bookRepository.findByNameAndPublisher(book.getName(), book.getPublisher());

        if(exists) {
            throw new BookNameAndPublisherException("Book name: " + book.getName() +
                    " and publisher: " + book.getPublisher() +  " already exists.");
        }

        Book newBook = bookRepository.save(book);

        return bookMapper.toResponseDTO(newBook);
    }

    public List<BookResponseDTO> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();

        if(bookList.isEmpty()) {
            throw new BookListIsEmptyException("Book list is empty.");
        }

        List<BookResponseDTO> bookResponseDTOList = bookList.stream()
                .map(book -> bookMapper.toResponseDTO(book))
                .collect(Collectors.toList());
        
        return bookResponseDTOList;
    }
}
