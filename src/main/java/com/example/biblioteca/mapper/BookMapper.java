package com.example.biblioteca.mapper;

import com.example.biblioteca.dtos.BookCreateDTO;
import com.example.biblioteca.dtos.BookResponseDTO;
import com.example.biblioteca.dtos.BookUpdateDTO;
import com.example.biblioteca.models.Book;
import org.springframework.stereotype.Component;

/*
Responsavel apenas por converter um objeto em DTO de resposta (toResponseDTO)
e converter um DTO de create e um DTO de update em objeto.
 */
@Component
public class BookMapper {

    public BookResponseDTO toResponseDTO(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getAuthor(),
                book.getPublisher()
        );

        return bookResponseDTO;
    }

    public Book toEntity(BookUpdateDTO bookUpdateDTO) {
        Book book = new Book();
        book.setAuthor(bookUpdateDTO.author());
        book.setGenre(bookUpdateDTO.genre());
        book.setTitle(bookUpdateDTO.title());
        book.setPublisher(bookUpdateDTO.publisher());

        return book;
    }

    public Book toEntity(BookCreateDTO bookCreateDTO) {
        Book book = new Book();
        book.setAuthor(bookCreateDTO.author());
        book.setGenre(bookCreateDTO.genre());
        book.setTitle(bookCreateDTO.title());
        book.setPublisher(bookCreateDTO.publisher());

        return book;
    }
}
