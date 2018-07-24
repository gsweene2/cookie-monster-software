package com.garrett.firstwebsite.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    /**
     * Dependency Injection of Item Repository, singleton.
     * Note that when the framework sees apache Derby database in the classpath,
     * it uses that database. No further connection needed!
     */

    @Autowired
    private PersonRepository personRepository;

    private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        //return items
        //
        List<Person> persons = new ArrayList<>();
        // Lambda
        personRepository.findAll().forEach(persons::add);
        // return the list
        return persons;
    }

    public Optional<Person> getPerson(Long id) {
        //return transactions
        return personRepository.findById(id);
    }

    public void addPerson(Person item){
        personRepository.save(item);
    }

    public void updatePerson(Long id, Person item){
        personRepository.save(item);
    }

    public void deletePerson(Long id) { personRepository.deleteById(id);}
}
