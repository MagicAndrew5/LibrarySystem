package com.unimib.lybrarysystem.controller;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.User;
import com.unimib.lybrarysystem.repository.BookRepository;
import com.unimib.lybrarysystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LibraryController {

    @Autowired
    private LibraryService service;
    @Autowired
    private BookRepository bookService;

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
        return "SearchBook";
    }

    @GetMapping("/registerNewUser")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "RegisterNewUser";
    }

    @GetMapping("/HomePage")
    public String showHomePage(Model model) {
        model.addAttribute("user", new User());

        List<Book> listBooks = service.findAllBooks();
        model.addAttribute("listBooks", listBooks);
        for (int i = 0; i < listBooks.size(); i++){
            System.out.println(listBooks.get(i).toString());
        }
        return "HomePage";
    }

    @PostMapping("/searchBooks")
    public String listBookPage(Book book, Model model) {
        List<Book> foundBooks = service.findByAttributes(book);
        model.addAttribute("foundBooks", foundBooks);
        return "listBookPage";
    }

  /*  //TODO
    @GetMapping("/listBookPage")
    public String getAllBooks(Model model) {
        List<Book> listBooks = bookService.getAllBooks();
        model.addAttribute("listBooks", listBooks);
        return "HomePage"; //
    }
    */



    @PostMapping("/checkAccount")
    public String checkAccount(User user, Model model, RedirectAttributes ra) {
        boolean validCheck = service.checkLogin(user);
        model.addAttribute("user", user);

        if(validCheck) {


            return "redirect:/HomePage";
        } else {
            ra.addFlashAttribute("message", "Username or password are incorrect, please try again");
            return "redirect:/signIn";
        }
    }


    @PostMapping("/saveAccount")
    public String addNewAccount(User user, Model model, RedirectAttributes ra) {
        boolean validAccount = service.checkSave(user);
        model.addAttribute("user", user);
        if(validAccount) {
            return "redirect:/HomePage";
        } else {
            ra.addFlashAttribute("message", "The user already exists, please try again");
            return "redirect:/registerNewUser";
        }
    }

}
