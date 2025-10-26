package com.example.biblioteca.controllers;

import com.example.biblioteca.dtos.BookCreateDTO;
import com.example.biblioteca.dtos.BookResponseDTO;
import com.example.biblioteca.dtos.BookUpdateDTO;
import com.example.biblioteca.mapper.BookMapper;
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

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookController(BookRepository bookRepository, BookMapper bookMapper) { //injeção de dependências via construtor
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookCreateDTO bookCreateDTO) {
        Book book = bookMapper.createBookFromDTO(bookCreateDTO);
        Book newBook = bookRepository.save(book);
        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(newBook);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){ //busca a lista dos book model.
        List<Book> bookList = bookRepository.findAll();
        List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();

        for(Book book : bookList) { //alterar para um metodo funcional
            BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(book);
            bookResponseDTOList.add(bookResponseDTO);
        }

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
