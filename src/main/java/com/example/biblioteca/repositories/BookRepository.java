package com.example.biblioteca.repositories;

import com.example.biblioteca.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    boolean findByTitleAndPublisher(String title, String publisher);
}
