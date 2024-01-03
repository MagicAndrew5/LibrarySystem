package com.unimib.lybrarysystem.controller;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.User;
import com.unimib.lybrarysystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class LibraryController {

    @Autowired
    private LibraryService service;

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/signIn")
    public String showSignInPage(Model model) {
        model.addAttribute("user", new User());
        return "SignInPage";
    }

    @GetMapping("/searchBook")
    public String showSearchBookPage(Model model) {
        model.addAttribute("books", new Book());
        return "SearchBook";
    }

    @GetMapping("/registerNewUser")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "RegisterNewUser";
    }

    @PostMapping("/searchBooks")
    public String listBookPage(Book book, Model model) {
        List<Book> foundBooks = service.findByAttributes(book);
        for (Book b : foundBooks) {
            System.out.println("Prova2:" + b);
        }
        model.addAttribute("foundBooks", foundBooks);
        System.out.println(model);
        return "listBookPage";
    }

    @PostMapping("/checkAccount")
    public String showHomePage(User user, Model model) {
        service.checkLogin(user);
        model.addAttribute("user", user);
        return "HomePage";
    }


    @PostMapping("/saveAccount")
    public String addNewAccount(User user, Model model) {
        service.checkSave(user);
        model.addAttribute("user", user);
        return "HomePage";
    }

}
