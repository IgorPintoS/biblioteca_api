package com.example.biblioteca.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

//entidade livros
@Entity
@Table(name = "LIVROS")

//classe está habilitada para passar por serializações
public class Book implements Serializable {
    //numero de controle de versão de cada uma das classes que forem serializadas
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String genre;
    private String author;
    private String publisher;

    public Book() {}

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

