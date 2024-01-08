package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.Book;
import com.unimib.lybrarysystem.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The repository for the user entity.
 * This interface extends CrudRepository and provides methods to perform CRUD operations on the user entity.
 */

public interface UserRepository extends CrudRepository<User, Integer> {

        /**
         * Finds a user in the repository that matches the provided email.
         *
         * @param email The email to be matched.
         * @return The user that matches the provided email.
         */
        @Query("SELECT u FROM User u WHERE u.email = :email")
        User findByEmail(String email);


        /**
         * Finds a user in the repository that matches the provided username and password.
         *
         * @param username The username to be matched.
         * @param password The password to be matched.
         * @return The user that matches the provided username and password.
         */
        @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
        User findByUsernamePassword(String username, String password);


        /**
         * Finds a user in the repository that matches the provided username.
         *
         * @param username The username to be matched.
         * @return The user that matches the provided username.
         */
        @Query("SELECT u FROM User u WHERE u.username = :username")
        User findByUsername(String username);
}