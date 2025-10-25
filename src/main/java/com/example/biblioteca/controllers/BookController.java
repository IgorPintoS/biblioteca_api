package com.example.biblioteca.controllers;

import com.example.biblioteca.dtos.BookCreateDTO;
import com.example.biblioteca.dtos.BookResponseDTO;
import com.example.biblioteca.dtos.BookUpdateDTO;
import com.example.biblioteca.models.Book;
import com.example.biblioteca.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BookController {

    private final BookRepository bookRepository; //ponto de injeção do repository

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookCreateDTO bookCreateDTO) {
        Book book = new Book();
        book.setAuthor(bookCreateDTO.author());
        book.setGenre(bookCreateDTO.genre());
        book.setName(bookCreateDTO.name());
        book.setPublisher(bookCreateDTO.publisher());

        Book newBook = bookRepository.save(book);

        BookResponseDTO bookResponseDTO = new BookResponseDTO(
                newBook.getId(),
                newBook.getAuthor(),
                newBook.getGenre(),
                newBook.getName(),
                newBook.getPublisher());

        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){ //busca a lista dos book model.
        List<Book> bookList = bookRepository.findAll();
        List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();

        for(Book book : bookList) {
            BookResponseDTO bookResponseDTO = new BookResponseDTO(
                    book.getId(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getName(),
                    book.getPublisher()
            );

            bookResponseDTOList.add(bookResponseDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTOList);
    }

    @GetMapping("/books/{id}") //buscando um unico livro
    public ResponseEntity<BookResponseDTO> getOneBook(@PathVariable UUID id){
        Optional<Book> bookOne = bookRepository.findById(id);

        if(bookOne.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Book book = bookOne.get();

        BookResponseDTO bookResponseDTO = new BookResponseDTO(
                book.getId(),
                book.getName(),
                book.getGenre(),
                book.getAuthor(),
                book.getPublisher()
        );

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO);
    }

    @PutMapping("/books/{id}") //editando um livro já criado
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable UUID id,
                                             @RequestBody @Valid BookUpdateDTO bookUpdateDTO) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = bookOptional.get();

        book.setAuthor(bookUpdateDTO.author());
        book.setGenre(bookUpdateDTO.genre());
        book.setName(bookUpdateDTO.name());
        book.setPublisher(bookUpdateDTO.publisher());

        bookRepository.save(book);

        BookResponseDTO bookResponseDTO = new BookResponseDTO(
                book.getId(),
                book.getAuthor(),
                book.getName(),
                book.getGenre(),
                book.getPublisher()
        );

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO);
    }

    @DeleteMapping("/books/{id}") //deletando um livro já criado
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id){
        Optional<Book> bookOne = bookRepository.findById(id);

        if(bookOne.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        bookRepository.delete(bookOne.get());

        return ResponseEntity.noContent().build();
    }
}
