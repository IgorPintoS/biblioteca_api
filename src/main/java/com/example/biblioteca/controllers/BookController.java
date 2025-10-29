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
        BookResponseDTO bookResponseDTO = bookService.createBook(bookCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){ //busca a lista dos book model.
        List<Book> bookList = bookRepository.findAll();

        List<BookResponseDTO> bookResponseDTOList = bookList.stream()
                .map(bookMapper::toResponseDTO).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTOList);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> getOneBook(@PathVariable UUID id){
        Optional<Book> bookOne = bookRepository.findById(id);

        if(bookOne.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Book book = bookOne.get();
        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(book);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable UUID id, @RequestBody @Valid BookUpdateDTO bookUpdateDTO) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = bookMapper.updateBookFromDTO(bookOptional.get(), bookUpdateDTO);
        bookRepository.save(book);

        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(book);

        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id){
        Optional<Book> bookOne = bookRepository.findById(id);

        if(bookOne.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        bookRepository.delete(bookOne.get());

        return ResponseEntity.noContent().build();
    }
}
