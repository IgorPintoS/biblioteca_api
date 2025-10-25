package com.example.biblioteca.repositories;

import com.example.biblioteca.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
//quando usamos o JpaRepository ja fica implicito que é um bean do tipo repository
public interface BookRepository extends JpaRepository<Book, UUID> {
}
