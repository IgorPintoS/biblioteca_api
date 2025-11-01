package com.example.biblioteca.controllers;

import com.example.biblioteca.dtos.BookCreateDTO;
import com.example.biblioteca.dtos.BookResponseDTO;
import com.example.biblioteca.dtos.BookUpdateDTO;
import com.example.biblioteca.mapper.BookMapper;
import com.example.biblioteca.models.Book;
import com.example.biblioteca.repositories.BookRepository;
import com.example.biblioteca.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) { //injeção de dependências via construtor
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookCreateDTO bookCreateDTO) {
        Book book = bookMapper.toEntity(bookCreateDTO);
        Book newBook = bookService.createBook(book);
        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(newBook); //tradução e save do novo book.

        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){ //busca a lista dos book model.
        List<Book> bookList = bookService.getAllBooks();

        List<BookResponseDTO> bookResponseDTOList = bookList.stream()
                .map(book -> bookMapper.toResponseDTO(book))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTOList);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> getOneBook(@PathVariable UUID id){
        Book book = bookService.getBook(id);
        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(book);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable UUID id, @RequestBody @Valid BookUpdateDTO bookUpdateDTO) {
        Book bookConverted = bookMapper.toEntity(bookUpdateDTO);
        Book bookUpdated = bookService.updateBook(id, bookConverted);
        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(bookUpdated);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id){
        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }
}
