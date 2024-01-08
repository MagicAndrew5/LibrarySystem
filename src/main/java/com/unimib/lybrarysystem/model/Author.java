package com.unimib.lybrarysystem.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an author entity in the library system.
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String surname;

    @Column(nullable = false, length = 45)
    private String birthDate;

    @Column(nullable = false, length = 45)
    private String nationality;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "author_collaborator",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "collaborator_id")
    )
    private Set<Author> collaborators = new HashSet<>();


    /**
     * Constructs an Author object with specified parameters.
     *
     * @param id            The unique identifier for the author.
     * @param name          The name of the author.
     * @param surname       The surname of the author.
     * @param birthDate     The birthdate of the author.
     * @param nationality   The nationality of the author
     * @param books         The set of books written by the author.
     * @param collaborators The set of collaborators (other authors) for the author.
     */
    public Author(Integer id, String name, String surname, String birthDate, String nationality, Set<Book> books, Set<Author> collaborators) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.books = books;
        this.collaborators = collaborators;
    }

    /**
     * Default constructor for the Author class.
     */
    public Author() {

    }

    /**
     * Retrieves the unique identifier of the author.
     *
     * @return The author's identifier.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the author.
     *
     * @param id The author's identifier.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the author.
     *
     * @return The author's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the author.
     *
     * @param name The author's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the surname of the author.
     *
     * @return The author's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the author.
     *
     * @param surname The author's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Retrieves the birth date of the author.
     *
     * @return The author's birth date.
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of the author.
     *
     * @param birthDate The author's birth date.
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retrieves the nationality of the author.
     *
     * @return The author's nationality.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the nationality of the author.
     *
     * @param nationality The author's nationality.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Retrieves the set of books written by the author.
     *
     * @return The set of books.
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * Sets the set of books written by the author.
     *
     * @param books The set of books.
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    /**
     * Retrieves the set of collaborators (other authors) for the author.
     *
     * @return The set of collaborators.
     */
    public Set<Author> getCollaborators() {
        return collaborators;
    }

    /**
     * Sets the set of collaborators (other authors) for the author.
     *
     * @param collaborators The set of collaborators.
     */
    public void setCollaborators(Set<Author> collaborators) {
        this.collaborators = collaborators;
    }


    /**
     * Returns a string representation of the Author object.
     *
     * @return A string representation of the Author.
     */
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", nationality='" + nationality + '\'' +
                ", books=" + books +
                ", collaborators=" + collaborators +
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
        if (!(o instanceof Author author)) return false;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(surname, author.surname) &&
                Objects.equals(birthDate, author.birthDate) && Objects.equals(nationality, author.nationality) &&
                Objects.equals(books, author.books) && Objects.equals(collaborators, author.collaborators);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    /*
    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthDate, nationality, books, collaborators);
    }
    */
}