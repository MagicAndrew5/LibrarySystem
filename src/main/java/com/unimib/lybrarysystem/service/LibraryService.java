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

    public boolean checkSave(User user) {
        User existingUser = repo.findByEmail(user.getEmail());
        if(existingUser != null) {
            return false;
        } else {
            repo.save(user);
            return true;
        }
    }

    public boolean checkLogin(User user) {
        if(repo.findByUsernamePassword(user.getUsername(), user.getPassword()) != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<Book> findByAttributes(Book book) {
        return repo.findByAttributes(book.getISBN(), book.getAuthor(), book.getTitle());
    }
}
