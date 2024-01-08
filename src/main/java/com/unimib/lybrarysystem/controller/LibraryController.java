package com.unimib.lybrarysystem.controller;

import com.unimib.lybrarysystem.model.Author;
import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.Genre;
import com.unimib.lybrarysystem.model.LibraryMember;
import com.unimib.lybrarysystem.model.User;
import com.unimib.lybrarysystem.service.LibraryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LibraryController {

    @Autowired
    private LibraryService service;

    // ------------------- GET METHODS -------------------

    /**
     * Handles the GET request for the start page.
     * @return The name of the start page view.
     */
    @GetMapping("/")
    public String showStartPage() {
        return "index";
    }

    /**
     * Handles the GET request for the sign in page.
     * @param model The model to add attributes to.
     * @return The name of the sign in page view.
     */
    @GetMapping("/signIn")
    public String showSignIn(Model model) {
        model.addAttribute("user", new User());
        return "SignInPage";
    }

    /**
     * Handles the GET request for the new user registration form.
     * @param model The model to add attributes to.
     * @return The name of the new user registration form view.
     */
    @GetMapping("/registerNewUser")
    public String showRegistrationUser(Model model) {
        model.addAttribute("user", new User());
        return "RegisterNewUser";
    }

    /**
     * Handles the GET request for the home page.
     * @param model The model to add attributes to.
     * @param session The current HTTP session.
     * @return The name of the home page view.
     */
    @GetMapping("/HomePage")
    public String showHomePage(Model model, HttpSession session) {

        // Get the user from the session
        User actualUser = (User) session.getAttribute("actualUser");
        System.out.println("User info: " + actualUser);

        // Get the library member ID from the session
        System.out.println("User Library Member ID info: " + actualUser.getLibraryMember().getId());

        // List book that the user has borrowed
        List<Book> borrowedBooks = new ArrayList<>();
        borrowedBooks = service.findBookByLibraryMember(actualUser.getLibraryMember());
        System.out.println("Books borrowed info: " + borrowedBooks);

        // List book that the user historical borrowed
        List<Book> historianBooks = new ArrayList<>();
        historianBooks = service.findHistoricalBookByLibraryMember(actualUser.getLibraryMember());
        System.out.println("Historian Books info: " + historianBooks);

        // List all books
        List<Book> listAllBooks = service.findAllBooks();
        System.out.println("All Books info: " + listAllBooks);

        model.addAttribute("user", actualUser);
        model.addAttribute("libraryMember", actualUser.getLibraryMember());

        // Model attributes for the books
        model.addAttribute("borrowedBooks", borrowedBooks);
        model.addAttribute("historianBook", historianBooks);
        model.addAttribute("listAllBooks", listAllBooks);

        return "HomePage";
    }

    /**
     * Handles the GET request for the book search page.
     * @param model The model to add attributes to.
     * @return The name of the book search page view.
     */
    @GetMapping("/searchBook")
    public String showSearchBook(Model model) {
        model.addAttribute("books", new Book());
        model.addAttribute("author", new Author());
        return "SearchBook";
    }

    /**
     * Handles the GET request for the full book search page.
     * Adds a new Book and Author object to the model for the search form.
     *
     * @param model The model to add attributes to.
     * @return The name of the full book search page view.
     */
    @GetMapping("/searchBookFull")
    public String showAdvancedSearchBook(Model model) {
        model.addAttribute("searchBookDetails", new Book());
        model.addAttribute("author", new Author());
        return "SearchBookFull";
    }

    /**
     * Handles the GET request for the book details page.
     * Retrieves a book from the repository that matches the provided ISBN and adds it to the model.
     *
     * @param model The model to add attributes to.
     * @param isbn The ISBN of the book to be retrieved.
     * @return The name of the book details page view.
     */
    @GetMapping("/detailBooks/{isbn}")
    public String showDetailBooks(Model model, @PathVariable("isbn") Integer isbn) {
        Book bookRetrieve = service.findBookByISBN(isbn);
        model.addAttribute("bookDetails", bookRetrieve);
        return "BookInformation";
    }

    // ------------------- POST METHODS -------------------

    /**
     * Handles the POST request for searching books.
     * @param book The book to search for.
     * @param model The model to add attributes to.
     * @return The name of the book list page view.
     */
    @PostMapping("/searchBooks")
    public String showListBook(Book book, Model model, RedirectAttributes ra) {

        List<Book> foundBooks = service.findBookByAttributes(book);
        //System.out.println("foundBooks Controller Info: " + foundBooks);
        model.addAttribute("foundBooks", foundBooks);
        model.addAttribute("books", new Book());

        if(foundBooks.isEmpty()) {
            ra.addFlashAttribute("message", "Your search result generated no matches, please try again");
            return "redirect:/searchBook";
        }
        else {
            return "listBookPage";
        }
    }

    /**
     * Handles the POST request for the advanced book search.
     * Retrieves books from the repository that match the provided publisher and author nationality and adds them to the model.
     *
     * @param publisher The publisher of the books to be retrieved.
     * @param nationality The nationality of the author of the books to be retrieved.
     * @param model The model to add attributes to.
     * @return The name of the book list page view.
     */
    @PostMapping("/searchAdvancedBooks")
    public String showListAdvancedBook(@RequestParam String publisher, @RequestParam String nationality, Model model, RedirectAttributes ra) {

        //System.out.println("publisher Controller Info: " + publisher);
        //System.out.println("nationality Controller Info: " + nationality);

        // Search books with these parameters
        List<Book> foundBooks = service.findBooksByPublisherAndAuthorNationality(publisher, nationality);
        //System.out.println("foundBooks Controller Info: " + foundBooks);
        model.addAttribute("foundBooks", foundBooks);
        model.addAttribute("book", new Book());

        if(foundBooks.isEmpty()) {
            ra.addFlashAttribute("message", "Your search result generated no matches, please try again");
            return "redirect:/searchBookFull";
        }
        else {
            return "listBookPage";
        }
    }

    /**
     * Handles the POST request for checking user account.
     * @param user The user to check.
     * @param libraryMember The library member to check.
     * @param model The model to add attributes to.
     * @param session The current HTTP session.
     * @param ra The redirect attributes.
     * @return The name of the redirect view.
     */
    @PostMapping("/checkAccount")
    public String checkAccount(User user, LibraryMember libraryMember, Model model, HttpSession session, RedirectAttributes ra) {
        boolean validCheck = service.checkLoginAccount(user);

        model.addAttribute("user", user);
        model.addAttribute("libraryMember", libraryMember);

        if(validCheck) {

            // Retrieve the actual user and actual library member from the database
            User actualUser = service.findUser(user);
            session.setAttribute("actualUser", actualUser);

            // Retrieve the actual library member from the database
            LibraryMember actualLibraryMember = service.findLibraryMember(actualUser.getLibraryMember());
            session.setAttribute("actualLibraryMember", actualLibraryMember);

            return "redirect:/HomePage";
        } else {
            ra.addFlashAttribute("message", "Username or password are incorrect, please try again");
            return "redirect:/signIn";
        }
    }

    /**
     * Handles the POST request for saving a new user account.
     * @param user The user to save.
     * @param libraryMember The library member to save.
     * @param model The model to add attributes to.
     * @param ra The redirect attributes.
     * @param session The current HTTP session.
     * @return The name of the redirect view.
     */
    @PostMapping("/saveAccount")
    public String addNewAccount(User user, LibraryMember libraryMember, Model model, RedirectAttributes ra, HttpSession session) {
        boolean validAccount = service.checkSaveAccount(user, libraryMember);

        model.addAttribute("user", user);
        model.addAttribute("libraryMember", libraryMember);

        if(validAccount) {

            // Retrieve the actual user and actual library member from the database
            User actualUser = service.findUser(user);
            session.setAttribute("actualUser", actualUser);

            // Retrieve the actual library member from the database
            LibraryMember actualLibraryMember = service.findLibraryMember(libraryMember);
            session.setAttribute("actualLibraryMember", actualLibraryMember);

            return "redirect:/HomePage";
        } else {
            ra.addFlashAttribute("message", "The user already exists, please try again");
            return "redirect:/registerNewUser";
        }
    }

    /**
     * Handles the POST request for adding a book to a library member.
     * @param book The book to add.
     * @param model The model to add attributes to.
     * @param session The current HTTP session.
     * @return The name of the redirect view.
     */
    @PostMapping("/addBook/{isbn}")
    public String addBook(Model model, @PathVariable("isbn") Integer isbn, HttpSession session, Book book) {

        LibraryMember libraryMember = (LibraryMember) session.getAttribute("actualLibraryMember");

        Book actualBook = service.findBookByISBN(isbn);

        session.setAttribute("actualBook", actualBook);
        service.addLinkBookToLibraryMember(actualBook, libraryMember);

        model.addAttribute("book", book);
        model.addAttribute("libraryMember", libraryMember);

        return "redirect:/HomePage";
    }

    /**
     * Handles the POST request for removing a book from a library member.
     * @param isbn The ISBN of the book to remove.
     * @param model The model to add attributes to.
     * @param session The current HTTP session.
     * @return The name of the redirect view.
     */
    @PostMapping("/removeBook/{isbn}")
    public String removeBook(@PathVariable("isbn") Integer isbn, Model model, HttpSession session) {

        // Retrieve the actual libraryMember and actual book from the database
        LibraryMember libraryMember = (LibraryMember) session.getAttribute("actualLibraryMember");
        Book actualBook = service.findBookByISBN(isbn);

        // Remove the book from the library member
        service.removeBookFromLibraryMember(actualBook, libraryMember.getId());

        model.addAttribute("bookRemove", actualBook);
        model.addAttribute("libraryMemberRemove", libraryMember);

        return "redirect:/HomePage";
    }
}