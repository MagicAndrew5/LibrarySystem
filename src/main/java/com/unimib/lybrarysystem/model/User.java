package com.unimib.lybrarysystem.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user in the library system.
 */

@Entity
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String surname;

    @Column(nullable = false, length = 45)
    private String username;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 45)
    private String password;

    @Column(nullable = false, length = 45)
    private String phoneNumber;

    @Column(nullable = false, length = 45)
    private String city;

    @OneToOne
    @JoinColumn(name = "library_member_id")
    private LibraryMember libraryMember;

    /**
     * Constructs a User object with specified parameters.
     *
     * @param id          The unique identifier for the user.
     * @param name        The name of the user.
     * @param surname     The surname of the user.
     * @param username    The username of the user.
     * @param email       The email of the user.
     * @param password    The password of the user.
     * @param phoneNumber The phone number of the user.
     * @param city        The city of the user.
     * @param libraryMember The library member of the user.
     */
    public User(Integer id, String name, String surname, String username, String email, String password, String phoneNumber, String city, LibraryMember libraryMember) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.libraryMember = libraryMember;
    }

    /**
     * Default constructor for the User class.
     */
    public User() {

    }

    /**
     * Retrieves the unique identifier of the user.
     *
     * @return The user's identifier.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The user's identifier.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the surname of the user.
     *
     * @return The user's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the user.
     *
     * @param surname The user's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;

    }

    /**
     * Retrieves the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email of the user.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the phone number of the user.
     *
     * @return The user's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber The user's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Retrieves the city of the user.
     *
     * @return The user's city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the user.
     *
     * @param city The user's city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieves the library member of the user.
     *
     * @return The user's library member.
     */
    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    /**
     * Sets the library member of the user.
     *
     * @param libraryMember The user's library member.
     */
    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name +
                ", surname='" + surname +
                ", username='" + username +
                ", email='" + email +
                ", password='" + password +
                ", phoneNumber='" + phoneNumber +
                ", city='" + city +
                ", libraryMember='" + libraryMember +
                '}';
    }
}
