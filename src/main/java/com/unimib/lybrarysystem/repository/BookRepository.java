package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.Book;
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
    List<Book> findByAttributes(Integer ISBN, String author, String title);

    @Query("SELECT b FROM Book b WHERE b.ISBN = :ISBN")
    Book findByISBN(Integer ISBN);

    /*
    @Modifying
    @Query("UPDATE Book b SET b.libraryMember.id = :id WHERE b.ISBN = :isbn")
    void addBookToLibraryMember(Integer isbn, Integer id);

     */
    @Query("SELECT b from Book b")
    List<Book> findAllBooks();
}




