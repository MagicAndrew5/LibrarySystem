package com.unimib.lybrarysystem.service;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.User;
import com.unimib.lybrarysystem.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository repo;

    public void checkSave(User user) {
        User existingUser = repo.findByEmail(user.getEmail());
        if(existingUser != null) {
            System.out.println("Email already in use");
        } else {
            repo.save(user);
            System.out.println("Email not in use");
        }
    }

    public void checkLogin(User user) {
        if(repo.findByUsernamePassword(user.getUsername(), user.getPassword()) != null) {
            System.out.println("Redirect to homePage");
        } else {

            System.out.println("Username or Password are invalid");
        }
    }


    public List<Book> findByAttributes(Book book) {
        return repo.findByAttributes(book.getISBN(), book.getAuthor(), book.getTitle());
    }
}
