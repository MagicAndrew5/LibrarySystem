package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    @Query("SELECT b from Book b")
    List<Book> findAllBooks();

}
