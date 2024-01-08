package com.unimib.lybrarysystem.service;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.LibraryMember;
import com.unimib.lybrarysystem.model.User;
import com.unimib.lybrarysystem.repository.BookRepository;
import com.unimib.lybrarysystem.repository.LibraryMemberRepository;
import com.unimib.lybrarysystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * Adds a link between a book and a library member in the system.
     * This represents the action of a library member borrowing a book.
     *
     * @param book The book to be borrowed.
     * @param libraryMember The library member who is borrowing the book.
     */
    public void addLinkBookToLibraryMember(Book book, LibraryMember libraryMember) {
        // Relationship between book and library member
        book.getBorrowingMembers().add(libraryMember);
        book.getHistorianMembers().add(libraryMember);
        bookRepo.save(book);
    }

    /**
     * Removes a book from a library member's borrowed books list.
     * This operation is performed within a transaction to ensure consistency.
     *
     * @param actualBook The book to be removed.
     * @param libraryMemberId The ID of the library member from whom the book is to be removed.
     */
    @Transactional
    public void removeBookFromLibraryMember(Book actualBook, Integer libraryMemberId) {

        // Retrieve the actual library member from the database
        LibraryMember libraryMember = libraryMemberRepo.findLibraryMemberWithBorrowedBooksById(libraryMemberId);

        // Now you can safely remove the book from the borrowedBooks collection
        libraryMember.removeBorrowedBook(actualBook);

        // Save the changes
        libraryMemberRepo.save(libraryMember);
    }

    /**
     * Finds a user in the repository that matches the provided user's username and password.
     *
     * @param user The user object containing the username and password to be matched.
     * @return The user that matches the provided username and password.
     */
    public User findUser(User user) {
        // Retrieve the actual user from the database
        return userRepo.findByUsernamePassword(user.getUsername(), user.getPassword());
    }

    /**
     * Finds a library member in the repository that matches the provided library member's ID.
     *
     * @param libraryMember The library member object containing the ID to be matched.
     * @return The library member that matches the provided ID.
     */
    public LibraryMember findLibraryMember(LibraryMember libraryMember) {
        // Retrieve the actual library member from the database
        return libraryMemberRepo.findLibraryMemberById(libraryMember.getId());
    }

    /**
     * Finds a book in the repository that matches the provided book's ISBN.
     *
     * @param book The book object containing the ISBN to be matched.
     * @return The book that matches the provided ISBN.
     */
    public Book findBook(Book book) {
        // Retrieve the actual book from the database
        return bookRepo.findByISBN(book.getISBN());
    }

    /**
     * Finds all books in the repository.
     *
     * @return A list of all books in the repository.
     */
    public List<Book> findAllBooks() {
        return bookRepo.findAllBooks();
    }

    /**
     * Finds books in the repository that match the provided attributes.
     *
     * @param book The book object containing the ISBN, author, and title to be matched.
     * @return A list of books that match the provided attributes.
     */
    public List<Book> findBookByAttributes(Book book) {
        return bookRepo.findBookByAttributes(book.getISBN(), book.getAuthor(), book.getTitle());
    }

    /**
     * Finds all ebooks in the repository.
     *
     * @return A list of all ebooks in the repository.
     */
    public List<Book> findEBookByAttributes(Book book) {
        return bookRepo.findEBookByAttributes(book.getISBN(), book.getAuthor(), book.getTitle());
    }

    /**
     * Finds books in the repository that are currently borrowed by the provided library member.
     *
     * @param libraryMember The library member whose borrowed books are to be found.
     * @return A list of books that are currently borrowed by the provided library member.
     */
    public List<Book> findBookByLibraryMember(LibraryMember libraryMember) {
        return bookRepo.findBookByLibraryMember(libraryMember);
    }

    /**
     * Finds books in the repository that were previously borrowed by the provided library member.
     *
     * @param libraryMember The library member whose historical borrowed books are to be found.
     * @return A list of books that were previously borrowed by the provided library member.
     */
    public List<Book> findHistoricalBookByLibraryMember(LibraryMember libraryMember) {
        return bookRepo.findHistoricalBookByLibraryMember(libraryMember);
    }

    /**
     * Finds a book in the repository that matches the provided ISBN.
     *
     * @param isbn The ISBN of the book to be found.
     * @return The book that matches the provided ISBN.
     */
    public Book findBookByISBN(Integer isbn) {
        return bookRepo.findByISBN(isbn);
    }

    /**
     * Finds books in the repository that match the provided publisher and author's nationality.
     *
     * @param publisher The publisher of the books to be retrieved.
     * @param nationality The nationality of the author of the books to be retrieved.
     * @return A list of books that match the provided publisher and author's nationality.
     */
    public List<Book> findBooksByPublisherAndAuthorNationality(String publisher, String nationality) {
        return bookRepo.findBooksByPublisherAndAuthorNationality(publisher, nationality);
    }
}