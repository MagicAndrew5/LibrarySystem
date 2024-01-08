package com.unimib.lybrarysystem.model;

import jakarta.persistence.*;

import java.util.*;

/**
 * Represents a library member entity in the library system.
 */
@Entity
@Table(name = "library_member")
public class LibraryMember {

    @Id
    @Column(nullable = false, length = 45)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String surname;

    @Column(nullable = false, length = 45)
    private String membershipDate;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "borrowingMembers")
    private List<Book> borrowedBooks = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "historianMembers")
    private List<Book> historianBooks = new ArrayList<>();


    /**
     * Constructs a LibraryMember object with specified parameters.
     *
     * @param id             The unique identifier for the library member.
     * @param name           The name of the library member.
     * @param surname        The surname of the library member.
     * @param membershipDate The membership date of the library member.
     * @param borrowedBooks  The set of books borrowed by the library member.
     * @param historianBooks The set of books historian borrowed by the library member.
     */
    public LibraryMember(Integer id, String name, String surname, String membershipDate, List<Book> borrowedBooks, List<Book> historianBooks) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.membershipDate = membershipDate;
        this.borrowedBooks = borrowedBooks;
        this.historianBooks = historianBooks;
    }

    /**
     * Default constructor for the LibraryMember class.
     */
    public LibraryMember() {

    }

    /**
     * Retrieves the unique identifier of the library member.
     *
     * @return The library member's identifier.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the library member.
     *
     * @param id The library member's identifier.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the library member.
     *
     * @return The library member's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the library member.
     *
     * @param name The library member's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the address of the library member.
     *
     * @return The library member's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the address of the library member.
     *
     * @param surname The library member's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Retrieves the membership date of the library member.
     *
     * @return The library member's membership date.
     */
    public String getMembershipDate() {
        return membershipDate;
    }

    /**
     * Sets the membership date of the library member.
     *
     * @param membershipDate The library member's membership date.
     */
    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    /**
     * Retrieves the list of books borrowed by the library member.
     *
     * @return The list of borrowed books.
     */
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Sets the list of books borrowed by the library member.
     *
     * @param borrowedBooks The list of borrowed books.
     */
    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    /**
     * Retrieves the list of books historian borrowed by the library member.
     *
     * @return The list of historian borrowed books.
     */
    public List<Book> getHistorianBooks() {
        return historianBooks;
    }

    /**
     * Sets the list of books historian borrowed by the library member.
     *
     * @param historianBooks The list of historian borrowed books.
     */
    public void setHistorianBooks(List<Book> historianBooks) {
        this.historianBooks = historianBooks;
    }

    /**
     * Removes a book from the list of books borrowed by this library member.
     * Also removes this library member from the list of library members who borrowed the book.
     *
     * @param book The book to be removed from the borrowed books list.
     */
    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
        book.getBorrowingMembers().remove(this);
    }

    /**
     * Returns a string representation of the LibraryMember object.
     *
     * @return A string representation of the LibraryMember.
     */
    @Override
    public String toString() {
        return "LibraryMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", membershipDate='" + membershipDate + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryMember that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) &&
                Objects.equals(membershipDate, that.membershipDate) && Objects.equals(borrowedBooks, that.borrowedBooks) &&
                Objects.equals(historianBooks, that.historianBooks);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, membershipDate, borrowedBooks, historianBooks);
    }
}
