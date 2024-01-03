package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibraryRepository extends CrudRepository<User, Integer> {

        public Long countById(Integer id);

        @Query("SELECT b FROM Book b WHERE b.ISBN = :ISBN AND b.author = :author AND b.title = :title")
        List<Book> findByAttributes(Integer ISBN, String author, String title);

        @Query("SELECT u FROM User u WHERE u.email = :email")
        User findByEmail(String email);

        @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
        User findByUsernamePassword(String username, String password);

}
