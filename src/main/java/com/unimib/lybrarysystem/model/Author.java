package com.unimib.lybrarysystem.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "author_collaborator",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "collaborator_id")
    )
    private Set<Author> collaborators = new HashSet<>();


    public Author(Integer id, String name, String surname, String birthDate, Set<Book> books, Set<Author> collaborators) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.books = books;
        this.collaborators = collaborators;
    }

    public Author() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Author> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(Set<Author> collaborators) {
        this.collaborators = collaborators;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", books=" + books +
                ", collaborators=" + collaborators +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(surname, author.surname) && Objects.equals(birthDate, author.birthDate) && Objects.equals(books, author.books) && Objects.equals(collaborators, author.collaborators);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthDate, books, collaborators);
    }
}
