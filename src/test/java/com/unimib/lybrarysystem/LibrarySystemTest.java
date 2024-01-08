package com.unimib.lybrarysystem;

import com.unimib.lybrarysystem.model.Author;
import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.EBook;
import com.unimib.lybrarysystem.model.Genre;
import com.unimib.lybrarysystem.repository.AuthorRepository;
import com.unimib.lybrarysystem.repository.BookRepository;
import com.unimib.lybrarysystem.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Library system.
 * It contains methods to test CRUD operations for the entities Book, Author, Genre, and LibraryMember.
 */
@SpringBootTest
public class LibrarySystemTest {

    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private GenreRepository genreRepo;

    /**
     * Test method for Book CRUD operations.
     * It tests the creation, reading, updating, and deletion of a Book entity.
     */
    @Test
    public void testBookCRUD() {

        Genre genre = new Genre();
        genre.setName("Horror");
        genre.setId(5);

        genreRepo.save(genre);

        // Create a new book
        Book book = new Book();

        book.setTitle("Harry Potter");
        book.setAuthor("J.K.Rowling");
        book.setPublisher("Feltrinelli");
        book.setGenre("Fantasy");
        book.setGenreList(genre);

        // Save the book
        book = bookRepo.save(book);
        assertNotNull(book.getISBN());

        // Read the book
        Book foundBook = bookRepo.findById(book.getISBN()).orElse(null);
        assertNotNull(foundBook);
        assertEquals(book.getTitle(), foundBook.getTitle());

        // Update the book
        book.setTitle("Narnia");
        book = bookRepo.save(book);
        foundBook = bookRepo.findById(book.getISBN()).orElse(null);
        assertNotNull(foundBook);
        assertEquals("Narnia", foundBook.getTitle());

        // Delete the book
        bookRepo.delete(book);
        foundBook = bookRepo.findById(book.getISBN()).orElse(null);
        assertNull(foundBook);
    }

    /**
     * Test method for Author CRUD operations.
     * It tests the creation, reading, updating, and deletion of an Author entity.
     */
    @Test
    public void testAuthorCRUD() {

        // Create a new author
        Author author = new Author();
        author.setName("Pippo");
        author.setSurname("Pluto");
        author.setBirthDate("01/01/2000");
        author.setNationality("Australian");

        // Save the author
        author = authorRepo.save(author);
        assertNotNull(author.getId());

        // Read the author
        Author foundAuthor = authorRepo.findById(author.getId()).orElse(null);
        assertNotNull(foundAuthor);
        assertEquals(author.getName(), foundAuthor.getName());

        // Update the author
        author.setName("Paperino");
        author = authorRepo.save(author);
        foundAuthor = authorRepo.findById(author.getId()).orElse(null);
        assertNotNull(foundAuthor);
        assertEquals("Paperino", foundAuthor.getName());

        // Delete the author
        authorRepo.delete(author);
        foundAuthor = authorRepo.findById(author.getId()).orElse(null);
        assertNull(foundAuthor);
    }

    /**
     * Test method for Genre CRUD operations.
     * It tests the creation, reading, updating, and deletion of a Genre entity.
     */
    @Test
    public void testGenreCRUD() {

        // Create a new genre
        Genre genre = new Genre();
        genre.setName("Comedy");

        // Save the genre
        genre = genreRepo.save(genre);
        assertNotNull(genre.getId());

        // Read the genre
        Genre foundGenre = genreRepo.findById(genre.getId()).orElse(null);
        assertNotNull(foundGenre);
        assertEquals(genre.getName(), foundGenre.getName());

        // Update the genre
        genre.setName("Horror");
        genre = genreRepo.save(genre);
        foundGenre = genreRepo.findById(genre.getId()).orElse(null);
        assertNotNull(foundGenre);
        assertEquals("Horror", foundGenre.getName());

        // Delete the genre
        genreRepo.delete(genre);
        foundGenre = genreRepo.findById(genre.getId()).orElse(null);
        assertNull(foundGenre);
    }

    /**
     * Test method for adding an author-book relationship.
     * It tests the creation of a new author and book, and the establishment of a relationship between them.
     */
    @Test
    public void testAddAuthorBookRelatioship() {

        Genre genre = new Genre();
        genre.setName("Horror");
        genre.setId(2);

        genreRepo.save(genre);

        Author author = new Author();

        author.setName("Alberto");
        author.setSurname("Blu");
        author.setNationality("Italian");
        author.setBirthDate("23/11/1999");

        Book book = new Book();

        book.setISBN(123456789);
        book.setAuthor("Alberto Blu");
        book.setPublisher("Mondadori");
        book.setTitle("Il libro degli incubi");
        book.setGenre("Fantasy");
        book.setGenreList(genre);

        book.getAuthors().add(author);

        authorRepo.save(author);
        bookRepo.save(book);
    }

    /**
     * Test method for adding an author-Ebook relationship.
     * It tests the creation of a new author and Ebook, and the establishment of a relationship between them.
     */
    @Test
    public void testAddAuthorEBookRelatioship() {

        Genre genre = new Genre();
        genre.setName("Giallo");
        genre.setId(3);

        genreRepo.save(genre);

        Author author = new Author();

        author.setName("Andrea");
        author.setSurname("Verde");
        author.setNationality("Spanish");
        author.setBirthDate("05/11/2000");

        EBook book = new EBook();

        book.setISBN(123456789);
        book.setAuthor("Andrea Verde");
        book.setPublisher("Feltrinelli");
        book.setTitle("Il libro");
        book.setGenre("Fantasy");
        book.setGenreList(genre);

        book.setFormat("PDF");
        book.setFileSizeMB(10);

        book.getAuthors().add(author);

        authorRepo.save(author);
        bookRepo.save(book);
    }
}
