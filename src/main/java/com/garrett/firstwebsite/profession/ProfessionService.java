package com.garrett.firstwebsite.profession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessionService {

    /**
     * Dependency Injection of Item Repository, singleton.
     * Note that when the framework sees apache Derby database in the classpath,
     * it uses that database. No further connection needed!
     */

    @Autowired
    private ProfessionRepository professionRepository;

    private List<Profession> professions = new ArrayList<>();

    public List<Profession> getProfessions() {
        //return professions
        //
        List<Profession> professions = new ArrayList<>();
        // Lambda
        professionRepository.findAll().forEach(professions::add);
        // return the list
        return professions;
    }

    public Optional<Profession> getProfession(Long id) {
        //return transactions
        return professionRepository.findById(id);
    }

    public void addProfession(Profession profession){
        professionRepository.save(profession);
    }

    public void updateProfession(Long id, Profession profession){
        professionRepository.save(profession);
    }

    public void deleteProfession(Long id) { professionRepository.deleteById(id);}
}
