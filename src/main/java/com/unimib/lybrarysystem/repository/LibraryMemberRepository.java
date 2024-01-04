package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.LibraryMember;
import org.springframework.data.repository.CrudRepository;

/**
 * The repository for the library member entity.
 */
public interface LibraryMemberRepository extends CrudRepository<LibraryMember, Integer> {

}
