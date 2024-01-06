package com.unimib.lybrarysystem.service;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.LibraryMember;
import com.unimib.lybrarysystem.model.User;
import com.unimib.lybrarysystem.repository.BookRepository;
import com.unimib.lybrarysystem.repository.LibraryMemberRepository;
import com.unimib.lybrarysystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class LibraryService {

    // ------------------- ATTRIBUTES -------------------
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final LibraryMemberRepository libraryMemberRepo;


    // ------------------- CONSTRUCTOR -------------------
    /**
     * Constructs a LibraryService object with specified parameters.
     *
     * @param userRepo The user repository.
     * @param bookRepo The book repository.
     * @param libraryMemberRepo The library member repository.
     */
    public LibraryService(UserRepository userRepo, BookRepository bookRepo, LibraryMemberRepository libraryMemberRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.libraryMemberRepo = libraryMemberRepo;
    }


    // ------------------- METHODS -------------------
    /**
     * Checks if the user is already registered in the system.
     *
     * @param user The user to check.
     * @param libraryMember The library member to check.
     * @return true if the user is not already registered in the system, false otherwise.
     */
    public boolean checkSaveAccount(User user, LibraryMember libraryMember) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser != null) {
            return false;
        } else {

            // Set the name and surname of the library member to the name and surname of the user
            libraryMember.setName(user.getName());
            libraryMember.setSurname(user.getSurname());

            // Generate random ID and set this value to the libraryMember
            Random random = new Random();
            int randomId = random.nextInt(900000000) + 100000000;
            libraryMember.setId(randomId);

            // Set the membership date to the current date
            libraryMember.setMembershipDate(LocalDate.now().toString());

            user.setLibraryMember(libraryMember);

            libraryMemberRepo.save(libraryMember);
            userRepo.save(user);
            return true;
        }
    }


    /**
     * Checks if the provided user's credentials match any existing user in the system.
     *
     * @param user The user object containing the username and password to be checked.
     * @return true if a user with the provided username and password exists, false otherwise.
     */

    public boolean checkLoginAccount(User user) {
        if(userRepo.findByUsernamePassword(user.getUsername(), user.getPassword()) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Finds books in the repository that match the provided attributes.
     *
     * @param book The book object containing the ISBN, author, and title to be matched.
     * @return A list of books that match the provided attributes.
     */
    public List<Book> findByAttributes(Book book) {
        return bookRepo.findByAttributes(book.getISBN(), book.getAuthor(), book.getTitle());
    }

    public void addLinkBookToLibraryMember(Book book, LibraryMember libraryMember) {
        // Relationship between book and library member
        book.getBorrowingMembers().add(libraryMember);
        bookRepo.save(book);
    }

    public User findUser(User user) {
        // Retrieve the actual user from the database
        return userRepo.findByUsernamePassword(user.getUsername(), user.getPassword());
    }

    public LibraryMember findLibraryMember(LibraryMember libraryMember) {
        // Retrieve the actual librarymember from the database
        return libraryMemberRepo.findLibraryMemberById(libraryMember.getId());
    }

    public Book findBook(Book book) {
        // Retrieve the actual book from the database
        return bookRepo.findByISBN(book.getISBN());
    }
}
