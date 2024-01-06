package com.unimib.lybrarysystem.controller;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.LibraryMember;
import com.unimib.lybrarysystem.model.User;
import com.unimib.lybrarysystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
public class LibraryController {

    @Autowired
    private LibraryService service;

    @GetMapping("/")
    public String showStartPage() {
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
        //model.addAttribute("libraryMember", new LibraryMember());
        return "SearchBook";
    }

    @GetMapping("/registerNewUser")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("libraryMember", new LibraryMember());
        return "RegisterNewUser";
    }

    @GetMapping("/HomePage")
    public String showHomePage(Model model, User user, LibraryMember libraryMember, Book book) {
        model.addAttribute("user", user);
        model.addAttribute("libraryMember", libraryMember);
        model.addAttribute("books", book);

        System.out.println("HomePage User Controller: " + user);
        System.out.println("HomePage LibraryMember Controller: " + libraryMember);
        System.out.println("HomePage Books Controller: " + book);

        return "HomePage";
    }


    // ------------------- POST MAPPING -------------------
    @PostMapping("/searchBooks")
    public String listBookPage(Book book, Model model) {
        List<Book> foundBooks = service.findByAttributes(book);
        model.addAttribute("foundBooks", foundBooks);
        return "listBookPage";
    }


    @PostMapping("/checkAccount")
    public String checkAccount(User user, LibraryMember libraryMember, Model model, RedirectAttributes ra) {
        boolean validCheck = service.checkLoginAccount(user);
        model.addAttribute("user", user);
        model.addAttribute("libraryMember", libraryMember);
        if(validCheck) {

            System.out.println("User Controller: " + user);
            System.out.println("LibraryMember Controller: " + libraryMember);

            return "redirect:/HomePage";
        } else {
            ra.addFlashAttribute("message", "Username or password are incorrect, please try again");
            return "redirect:/signIn";
        }
    }


    @PostMapping("/saveAccount")
    public String addNewAccount(User user, LibraryMember libraryMember, Model model, RedirectAttributes ra) {
        boolean validAccount = service.checkSaveAccount(user, libraryMember);
        model.addAttribute("user", user);
        model.addAttribute("libraryMember", libraryMember);
        if(validAccount) {

            System.out.println("User Controller: " + user);
            System.out.println("LibraryMember Controller: " + libraryMember);

            return "redirect:/HomePage";
        } else {
            ra.addFlashAttribute("message", "The user already exists, please try again");
            return "redirect:/registerNewUser";
        }
    }

    @PostMapping("/addBook")
    public String addBook(Book book, LibraryMember libraryMember, Model model) {

        service.addLinkBookToLibraryMember(book, libraryMember);

        model.addAttribute("book", book);
        model.addAttribute("libraryMember", libraryMember);

        return "redirect:/HomePage";
    }
}
