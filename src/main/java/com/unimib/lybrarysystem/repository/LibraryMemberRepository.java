package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.LibraryMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * The repository for the library member entity.
 */
public interface LibraryMemberRepository extends CrudRepository<LibraryMember, Integer> {

    /*
    @Modifying
    @Query("UPDATE LibraryMember lm SET lm.borrowedBooks.ISBN = :isbn WHERE lm.id = :id")
    void addLibraryMemberToBook(Integer id, Integer isbn);

     */

    @Query("SELECT lm FROM LibraryMember lm WHERE lm.id = :id")
    LibraryMember findLibraryMemberById(Integer id);
}
