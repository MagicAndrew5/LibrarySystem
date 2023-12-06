package com.unimib.lybrarysystem.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ISBN;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 45)
    private String author;

    @Column(nullable = false, length = 45)
    private String genre;

    @Column(nullable = false, length = 45)
    private String publisher;


    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_ISBN")
    )
    private Set<Author> authors = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genreList;

    @ManyToMany(mappedBy = "borrowedBooks")
    private Set<LibraryMember> borrowingMembers = new HashSet<>();


    public Book(Integer ISBN, String title, String author, String genre, String publisher, Set<Author> authors, Genre genreList, Set<LibraryMember> borrowingMembers) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.authors = authors;
        this.genreList = genreList;
        this.borrowingMembers = borrowingMembers;
    }


    public Book() {

    }


    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Genre getGenreList() {
        return genreList;
    }

    public void setGenreList(Genre genreList) {
        this.genreList = genreList;
    }

    public Set<LibraryMember> getBorrowingMembers() {
        return borrowingMembers;
    }

    public void setBorrowingMembers(Set<LibraryMember> borrowingMembers) {
        this.borrowingMembers = borrowingMembers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", authors=" + authors +
                ", genreList=" + genreList +
                ", borrowingMembers=" + borrowingMembers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(ISBN, book.ISBN) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre) && Objects.equals(publisher, book.publisher) && Objects.equals(authors, book.authors) && Objects.equals(genreList, book.genreList) && Objects.equals(borrowingMembers, book.borrowingMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, title, author, genre, publisher, authors, genreList, borrowingMembers);
    }
}
