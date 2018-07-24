package com.garrett.firstwebsite.profession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/profession")
public class ProfessionController {

    /**
     * Dependency Injection of ProfessionService, singleton.
     */
    @Autowired
    ProfessionService professionService;

    /**
     * Get all professions
     */
    @RequestMapping("")
    public String getAllProfessions(Model model) {
        model.addAttribute("allProfessions", professionService.getProfessions());
        return "profession/allProfessions";
    }

    @RequestMapping("/create")
    public String createProfessionForm(Model model){
        model.addAttribute("professionPassed", new Profession());
        return "profession/create";
    }

    @PostMapping("/create")
    public String createProfessionResult(@ModelAttribute Profession profession){
        professionService.addProfession(profession);
        return "profession/result";
    }

    /**
     * Get Profession
     *
     */
    @RequestMapping("/{id}")
    public Optional<Profession> getProfession(@PathVariable("id") Long id) {
        return  professionService.getProfession(id);
    }

    /**
     * Add Profession
     */
    @RequestMapping(method= RequestMethod.POST, value="")
    public void addProfession(@RequestBody Profession profession) {
        professionService.addProfession(profession);
    }

    /**
     * Update Profession
     */
    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public void updateProfession(@RequestBody Profession profession, @PathVariable Long id){
        professionService.updateProfession(id, profession);
    }

    /**
     * Delete Person
     */
    @RequestMapping(method=RequestMethod.DELETE,value="")
    public void deleteProfession(@PathVariable Long id) {
        professionService.deleteProfession(id);
    }

    /**
     * Load all Profession
     */
    @RequestMapping(method=RequestMethod.GET, value="loadall")
    public void loadAllProfessions(){
        ArrayList<Profession> professionsList = new ArrayList<Profession>();
        professionsList.add(new Profession(1,"Software Engineer"));
        professionsList.add(new Profession(2,"Firefighter"));
        for ( Profession profession : professionsList ){
            professionService.addProfession(profession);
        }
    }
}
