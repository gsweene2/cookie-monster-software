package com.garrett.firstwebsite.person;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByUserId(Long userId);
}
