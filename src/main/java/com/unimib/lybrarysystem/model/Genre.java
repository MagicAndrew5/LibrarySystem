package com.unimib.lybrarysystem.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * The Genre class represents a literary genre in the library system.
 * It is used to categorize books based on their content or style.
 */
@Entity
@Table(name = "genre")
public class Genre {

    /**
     * The unique identifier (ID) for the genre.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the genre.
     */
    @Column(nullable = false, length = 45)
    private String name;

    /**
     * Constructs a Genre object with specified ID and name.
     *
     * @param id   The unique identifier for the genre.
     * @param name The name of the genre.
     */
    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Default constructor for the Genre class.
     */
    public Genre() {
    }

    /**
     * Retrieves the unique identifier (ID) of the genre.
     *
     * @return The ID of the genre.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier (ID) for the genre.
     *
     * @param id The ID to set for the genre.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the genre.
     *
     * @return The name of the genre.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the genre.
     *
     * @param name The name to set for the genre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Provides a string representation of the Genre object.
     *
     * @return A string representation including ID and name of the genre.
     */
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Checks if this Genre object is equal to another object based on ID and name.
     *
     * @param o The object to compare with this Genre.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre genre)) return false;
        return Objects.equals(id, genre.id) && Objects.equals(name, genre.name);
    }

    /**
     * Generates a hash code for the Genre object based on its ID and name.
     *
     * @return The hash code for the Genre object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
