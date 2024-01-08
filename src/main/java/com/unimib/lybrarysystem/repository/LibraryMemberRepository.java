package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.LibraryMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * The repository for the library member entity.
 * This interface extends CrudRepository and provides methods to perform CRUD operations on the library member entity.
 */
public interface LibraryMemberRepository extends CrudRepository<LibraryMember, Integer> {

    /**
     * Finds a library member in the repository that matches the provided ID.
     *
     * @param id The ID of the library member to be matched.
     * @return The library member that matches the provided ID.
     */
    @Query("SELECT lm FROM LibraryMember lm WHERE lm.id = :id")
    LibraryMember findLibraryMemberById(Integer id);

    /**
     * Finds a library member in the repository that matches the provided ID.
     * Also fetches the borrowed books of the library member.
     *
     * @param id The ID of the library member to be matched.
     * @return The library member that matches the provided ID, with the borrowed books collection initialized.
     */
    @Query("SELECT lm FROM LibraryMember lm JOIN FETCH lm.borrowedBooks WHERE lm.id = :id")
    LibraryMember findLibraryMemberWithBorrowedBooksById(Integer id);
}
