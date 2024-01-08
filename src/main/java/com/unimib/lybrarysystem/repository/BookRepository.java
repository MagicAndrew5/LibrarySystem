package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.LibraryMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
    @Query("SELECT b FROM Book b WHERE (:ISBN IS NULL OR b.ISBN = :ISBN) AND (:author IS NULL OR b.author = :author) AND (:title IS NULL OR b.title = :title)")
    List<Book> findBookByAttributes(@Param("ISBN") Integer ISBN, @Param("author") String author, @Param("title") String title);

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

    /**
     * Finds books in the repository that match the provided publisher and author's nationality.
     *
     * @param publisher The publisher of the books to be retrieved.
     * @param nationality The nationality of the author of the books to be retrieved.
     * @return A list of books that match the provided publisher and author's nationality.
     */
    @Query("SELECT b FROM Book b JOIN b.authors a WHERE b.publisher = :publisher AND a.nationality = :nationality")
    List<Book> findBooksByPublisherAndAuthorNationality(String publisher, String nationality);

    /**
     * Finds ebooks in the repository that match the provided ISBN, author, and title.
     * If any of these parameters are null, it will ignore that parameter in the search.
     *
     * @param ISBN The ISBN of the ebooks to be retrieved.
     * @param author The author of the ebooks to be retrieved.
     * @param title The title of the ebooks to be retrieved.
     * @return A list of ebooks that match the provided ISBN, author, and title.
     */
    @Query("SELECT b FROM Book b WHERE TYPE(b) = EBook AND (:ISBN IS NULL OR b.ISBN = :ISBN) AND (:author IS NULL OR b.author = :author) AND (:title IS NULL OR b.title = :title)")
    List<Book> findEBookByAttributes(@Param("ISBN") Integer ISBN, @Param("author") String author, @Param("title") String title);
}




