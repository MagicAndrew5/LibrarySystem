package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.LibraryMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The repository for the book entity.
 * This interface extends CrudRepository and provides methods to perform CRUD operations on the book entity.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {

    /**
     * Finds books in the repository that match the provided ISBN, author, and title.
     *
     * @param ISBN The ISBN of the book to be matched.
     * @param author The author of the book to be matched.
     * @param title The title of the book to be matched.
     * @return A list of books that match the provided ISBN, author, and title.
     */
    @Query("SELECT b FROM Book b WHERE b.ISBN = :ISBN AND b.author = :author AND b.title = :title")
    List<Book> findBookByAttributes(Integer ISBN, String author, String title);

    /**
     * Finds a book in the repository that matches the provided ISBN.
     *
     * @param ISBN The ISBN of the book to be matched.
     * @return The book that matches the provided ISBN.
     */
    @Query("SELECT b FROM Book b WHERE b.ISBN = :ISBN")
    Book findByISBN(Integer ISBN);

    /**
     * Retrieves all books from the repository.
     *
     * @return A list of all books in the repository.
     */
    @Query("SELECT b from Book b")
    List<Book> findAllBooks();

    /**
     * Finds books in the repository that are currently borrowed by the provided library member.
     *
     * @param libraryMember The library member whose borrowed books are to be found.
     * @return A list of books that are currently borrowed by the provided library member.
     */
    @Query("SELECT b FROM Book b WHERE :libraryMember MEMBER OF b.borrowingMembers")
    List<Book> findBookByLibraryMember(LibraryMember libraryMember);

    /**
     * Finds books in the repository that were previously borrowed by the provided library member.
     *
     * @param libraryMember The library member whose historical borrowed books are to be found.
     * @return A list of books that were previously borrowed by the provided library member.
     */
    @Query("SELECT b FROM Book b WHERE :libraryMember MEMBER OF b.historianMembers")
    List<Book> findHistoricalBookByLibraryMember(LibraryMember libraryMember);

}




