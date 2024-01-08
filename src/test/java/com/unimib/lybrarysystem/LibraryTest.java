package com.unimib.lybrarysystem;

import com.unimib.lybrarysystem.model.Author;
import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.Genre;
import com.unimib.lybrarysystem.repository.AuthorRepository;
import com.unimib.lybrarysystem.repository.BookRepository;
import com.unimib.lybrarysystem.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class LibraryTest {

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private GenreRepository genreRepo;
    @Test
    public void testAddAuthorBookRelatioship() {

        Genre genre = new Genre();
        genre.setName("Giallo");
        genre.setId(3);

        genreRepo.save(genre);

        Author author = new Author();

        author.setName("Andrea");
        author.setSurname("Verde");
        author.setNationality("Spanish");
        author.setBirthDate("05/11/2000");

        Book book = new Book();

        book.setISBN(123456789);
        book.setAuthor("Andrea Verde");
        book.setPublisher("Feltrinelli");
        book.setTitle("Il libro");
        book.setGenre("Fantasy");
        book.setGenreList(genre);

        book.getAuthors().add(author);

        //authorRepo.save(author);
        bookRepo.save(book);

    }
}
