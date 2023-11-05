package com.example.biblioteca.controllers;

import com.example.biblioteca.dtos.BookRecordDto;
import com.example.biblioteca.models.BookModel;
import com.example.biblioteca.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//bean que tem uma implementação de API rest
@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository; //ponto de injeção da inteface.

    @PostMapping("/books") //inserindo livros
    public ResponseEntity<BookModel> saveBook(@RequestBody @Valid BookRecordDto bookRecordDto) {
        var bookModel = new BookModel();
        BeanUtils.copyProperties(bookRecordDto, bookModel); //conversão de DTO para model.
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(bookModel));
    }
    @GetMapping("/books") //buscando todos os livros
    public ResponseEntity<List<BookModel>> getAllBooks(){ //busca a lista dos book model.
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll()); //retona somente com status ok e no corpo todos os books do repositorio
    }
    @GetMapping("/books/{id}") //buscando um unico livro
    public ResponseEntity<Object> getOneBook(@PathVariable(value="id")UUID id){
        Optional<BookModel> bookOne = bookRepository.findById(id);
        if(bookOne.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookOne.get());
    }
    @PutMapping("/books/{id}") //editando um livro já criado
    public ResponseEntity<Object> updateBook(@PathVariable(value="id") UUID id,
                                             @RequestBody @Valid BookRecordDto bookRecordDto) {
        Optional<BookModel> bookOne = bookRepository.findById(id);
        if (bookOne.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
        var bookModel = bookOne.get();
        BeanUtils.copyProperties(bookRecordDto, bookModel);
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(bookModel));
    }
    @DeleteMapping("/books/{id}") //deletando um livro já criado
    public ResponseEntity<Object> deleteBook(@PathVariable(value="id") UUID id){
        Optional<BookModel> bookOne = bookRepository.findById(id);
        if(bookOne.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
        bookRepository.delete(bookOne.get());
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully.");
    }

}
