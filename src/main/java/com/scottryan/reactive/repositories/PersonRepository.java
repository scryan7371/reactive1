package com.scottryan.reactive.repositories;

import com.scottryan.reactive.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository for accessing the Person object.
 */
public interface PersonRepository extends CrudRepository<Person, Integer>,
        PagingAndSortingRepository<Person, Integer> {
}
