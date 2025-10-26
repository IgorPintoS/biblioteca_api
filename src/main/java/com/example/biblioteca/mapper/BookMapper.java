package com.example.biblioteca.mapper;

import com.example.biblioteca.dtos.BookCreateDTO;
import com.example.biblioteca.dtos.BookResponseDTO;
import com.example.biblioteca.dtos.BookUpdateDTO;
import com.example.biblioteca.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookResponseDTO toResponseDTO(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO(
                book.getId(),
                book.getAuthor(),
                book.getName(),
                book.getGenre(),
                book.getPublisher()
        );

        return bookResponseDTO;
    }

    public Book updateBookFromDTO(Book book, BookUpdateDTO bookUpdateDTO) {
        book.setAuthor(bookUpdateDTO.author());
        book.setGenre(bookUpdateDTO.genre());
        book.setName(bookUpdateDTO.name());
        book.setPublisher(bookUpdateDTO.publisher());

        return book;
    }

    public Book createBookFromDTO(BookCreateDTO bookCreateDTO) {
        Book book = new Book();
        book.setAuthor(bookCreateDTO.author());
        book.setGenre(bookCreateDTO.genre());
        book.setName(bookCreateDTO.name());
        book.setPublisher(bookCreateDTO.publisher());

        return book;
    }
}
