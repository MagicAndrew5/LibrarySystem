package com.unimib.lybrarysystem.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a book entity in the library system.
 */
@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "book_type")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ISBN;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 45)
    private String author;

    @Column(nullable = false, length = 45)
    private String publisher;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_ISBN"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genreList;

    @ManyToMany
    @JoinTable(
            name = "library_member_book",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "library_member_id")
    )
    private List<LibraryMember> borrowingMembers;

    @ManyToMany
    @JoinTable(
            name = "library_member_book_historian",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "library_member_id")
    )
    private List<LibraryMember> historianMembers;

    /**
     * Constructs a Book object with specified parameters.
     *
     * @param ISBN              The unique identifier for the book.
     * @param title             The title of the book.
     * @param author            The author of the book.
     * @param publisher         The publisher of the book.
     * @param authors           The set of authors who wrote the book.
     * @param genreList         The genre object associated with the book.
     * @param libraryMember     The library members who borrowed the book.
     * @param historianMembers  The library members who historian borrowed the book.
     */
    public Book(Integer ISBN, String title, String author, String publisher, Set<Author> authors, Genre genreList, List<LibraryMember> libraryMember, List<LibraryMember> historianMembers) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.authors = authors;
        this.genreList = genreList;
        this.borrowingMembers = libraryMember;
        this.historianMembers = historianMembers;
    }

    /**
     * Default constructor for the Book class.
     */
    public Book() {

    }

    /**
     * Retrieves the unique identifier (ISBN) of the book.
     *
     * @return The ISBN of the book.
     */
    public Integer getISBN() {
        return ISBN;
    }

    /**
     * Sets the unique identifier (ISBN) for the book.
     *
     * @param ISBN The ISBN to set for the book.
     */
    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Retrieves the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title for the book.
     *
     * @param title The title to set for the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author for the book.
     *
     * @param author The author to set for the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Retrieves the publisher of the book.
     *
     * @return The publisher of the book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher for the book.
     *
     * @param publisher The publisher to set for the book.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Retrieves the set of authors who wrote the book.
     *
     * @return The set of authors for the book.
     */
    public Set<Author> getAuthors() {
        return authors;
    }

    /**
     * Sets the set of authors who wrote the book.
     *
     * @param authors The set of authors to set for the book.
     */
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    /**
     * Retrieves the genre object associated with the book.
     *
     * @return The genre object associated with the book.
     */
    public Genre getGenreList() {
        return genreList;
    }

    /**
     * Sets the genre object associated with the book.
     *
     * @param genreList The genre object to set for the book.
     */
    public void setGenreList(Genre genreList) {
        this.genreList = genreList;
    }

    /**
     * Retrieves the set of library members who borrowed the book.
     *
     * @return The set of library members who borrowed the book.
     */

    public List<LibraryMember> getBorrowingMembers() {
        return borrowingMembers;
    }

    /**
     * Sets the set of library members who borrowed the book.
     *
     * @param borrowingMembers The set of library members to set for the book.
     */
    public void setBorrowingMembers(List<LibraryMember> borrowingMembers) {
        this.borrowingMembers = borrowingMembers;
    }

    /**
     * Retrieves the list of library members who have previously borrowed the book.
     *
     * @return A list of library members who have previously borrowed the book.
     */
    public List<LibraryMember> getHistorianMembers() {
        return historianMembers;
    }

    /**
     * Sets the list of library members who have previously borrowed the book.
     *
     * @param historianMembers The list of library members to set.
     */
    public void setHistorianMembers(List<LibraryMember> historianMembers) {
        this.historianMembers = historianMembers;
    }

    /**
     * Returns a string representation of the Book object.
     *
     * @return A string representation of the Book.
     */
    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
    // understand why this is not working with authors and borrowingMembers

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(ISBN, book.ISBN) && Objects.equals(title, book.title) && Objects.equals(author, book.author) &&
                Objects.equals(publisher, book.publisher) && Objects.equals(authors, book.authors) &&
                Objects.equals(genreList, book.genreList) && Objects.equals(borrowingMembers, book.borrowingMembers) &&
                Objects.equals(historianMembers, book.historianMembers);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(ISBN, title, author, publisher, authors, genreList, borrowingMembers, historianMembers);
    }
}
