package com.unimib.lybrarysystem.repository;

import com.unimib.lybrarysystem.model.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * The repository for the author entity.
 * This interface extends CrudRepository and provides methods to perform CRUD operations on the Author entity.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
