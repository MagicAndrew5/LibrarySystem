package com.unimib.lybrarysystem.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String showSignInPage(Model model) {
        model.addAttribute("user", new User());
        return "SignInPage";
    }

    //TODO
    @GetMapping("/detailBooks/{isbn}")
    public String detailBooks(Model model, @PathVariable("isbn") Integer isbn) {
        Book bookRetrieve = service.findBookByISBN(isbn);
        model.addAttribute("bookDetails", bookRetrieve);
        return "BookInformation";
    }


    /**
     * Handles the GET request for the new user registration form.
     * @param model The model to add attributes to.
     * @return The name of the new user registration form view.
     */
    @GetMapping("/registerNewUser")
    public String showNewUserForm(Model model) {
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

        // Get the book from the session
        Book actualBook = (Book) session.getAttribute("actualBook");
        System.out.println("Book info: " + actualBook);

        // Get the library member from the session
        LibraryMember actualLibraryMember = (LibraryMember) session.getAttribute("actualLibraryMember");
        System.out.println("LibraryMember info: " + actualLibraryMember);

        model.addAttribute("user", actualUser);
        model.addAttribute("libraryMember", actualLibraryMember);
        model.addAttribute("books", actualBook);

        List<Book> listBooks = service.findAllBooks();
        model.addAttribute("listBooks", listBooks);
        for (int i = 0; i < listBooks.size(); i++){
            System.out.println(listBooks.get(i).toString());
        }

        return "HomePage";
    }

    /**
     * Handles the GET request for the book search page.
     * @param model The model to add attributes to.
     * @return The name of the book search page view.
     */
    @GetMapping("/searchBook")
    public String showSearchBookPage(Model model) {
        model.addAttribute("books", new Book());
        return "SearchBook";
    }

    // Mapping to display the RegisterNewBook page
    @GetMapping("/registerNewBook")
    public String registerNewBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "RegisterNewBookPage";
    }

    // ------------------- POST METHODS -------------------

    /**
     * Handles the POST request for searching books.
     * @param book The book to search for.
     * @param model The model to add attributes to.
     * @return The name of the book list page view.
     */
    @PostMapping("/searchBooks")
    public String listBookPage(Book book, Model model) {
        List<Book> foundBooks = service.findByAttributes(book);
        model.addAttribute("foundBooks", foundBooks);
        return "listBookPage";
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

            User actualUser = service.findUser(user);
            session.setAttribute("actualUser", actualUser);

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

            User actualUser = service.findUser(user);
            session.setAttribute("actualUser", actualUser);

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
    @PostMapping("/addBook")
    public String addBook(Model model, HttpSession session, Book book) {

        LibraryMember libraryMember = (LibraryMember) session.getAttribute("actualLibraryMember");

        Book actualBook = service.findBook(book);

        session.setAttribute("actualBook", actualBook);
        service.addLinkBookToLibraryMember(actualBook, libraryMember);

        model.addAttribute("book", book);
        model.addAttribute("libraryMember", libraryMember);

        return "redirect:/HomePage";
    }

    // Mapping to handle the form submission for saving a new book
    @PostMapping("/saveBook")
    public String saveBook( Book book, Model model ) {

        service.saveBook(book);
        List<Book> allBooks = service.findAllBooks();

        model.addAttribute("listBooks", allBooks);
        return "redirect:/HomePage";
    }

}