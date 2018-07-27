package com.garrett.firstwebsite.person;

import com.garrett.firstwebsite.Request.PrettyRequest;
import com.garrett.firstwebsite.Request.Request;
import com.garrett.firstwebsite.Request.RequestRepository;
import com.garrett.firstwebsite.Request.RequestService;
import com.garrett.firstwebsite.profession.ProfessionService;

import com.garrett.firstwebsite.user.User;
import com.garrett.firstwebsite.user.UserRepository;
import com.garrett.firstwebsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.Validate;

import java.util.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    /**
     * Dependency Injection of UserService, singleton.
     */
    @Autowired
    PersonService personService;

    @Autowired
    ProfessionService professionService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserService userService;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestService requestService;

    /**
     * Get all persons
     */
    @RequestMapping("")
    public ModelAndView getAllPersons(ModelAndView model) {
        model.setViewName("person/allPersons");
        model.addObject("allPersons", personService.getPersons());
        return model;
    }

    @RequestMapping("/register")
    @ModelAttribute("personPassed")
    public ModelAndView registerPersonForm(ModelAndView model){
        model.setViewName("person/register");
        model.addObject("personPassed", new Person());
        model.addObject("professionsList", professionService.getProfessions());
        return model;
    }

    @GetMapping("/form")
    public ModelAndView formSearch(ModelAndView model){
        model.setViewName("person/form");
        model.addObject("prettyPerson", new PrettyPerson());
        return model;
    }

    @PostMapping("/register")
    public ModelAndView createPersonResult(@ModelAttribute Person person, Authentication authentication){
        ModelAndView model = new ModelAndView();
        person.setUserId(userRepository.findByEmail(authentication.getName()).getId());
        personService.addPerson(person);
        model.addObject("person",person);
        model.setViewName("person/dashboard");
        return model;
    }

    @GetMapping("/dashboard")
    public ModelAndView getUserDashboard(Authentication authentication, ModelAndView model){
        model.setViewName("person/dashboard");
        //get the current logged in person by
        //using the Authentication to get EMAIL,
        //and returning the user associated with the email

        Validate.notNull(authentication.getName(), "Garrett: When loading dashbaord, authentication has no name");
        User thisUser = userRepository.findByEmail(authentication.getName());
        Validate.notNull(thisUser, "Garrett: When loading the dashboard, could not find the user in the user repo!");
        //Log4J2LoggingSystem logger = new Log4J2LoggingSystem(ClassLoader.getSystemClassLoader());



        //Now that we have the user, we can get the userId
        //and perform a search in the personRepository to find out
        //if we have data for them

        Validate.notNull(thisUser.getId(), "Garrett: The found User Object has a null userid");
        Person thisPerson = personRepository.findByUserId(thisUser.getId());

        // DO NOT VALIDATE IF THEY ARE NOT NULL, WILL BE CREATED IN NEXT BLOCK!
        //Validate.notNull(thisUser.getId(), "Garrett: The current User obj's id is null");
        //Validate.notNull(thisPerson, "Garrett: Could not find Person based on User id");

        // ToDo: Make User class primary key a long!!

        if (thisPerson == null){
            model.addObject("personPassed", new Person());
            model.addObject("professionsList", professionService.getProfessions());
            model.setViewName("person/register");
            return model;
        }

        long thispersonId = thisUser.getId();
        personRepository.findByUserId(thispersonId);

        //Now that we may have a person, we can determine if they need to fill out info!
        model.addObject("thisuser",thisUser);
        model.addObject("person", thisPerson);
        model.addObject("profession",professionService.getProfession(Integer.toUnsignedLong(thisPerson.getProfessionId())).get());

        // ************************************** //
        // *********** Other Users ************** //
        // ************************************** //

        List<Person> otherUsers = personService.getPersons();
        ArrayList<PrettyPerson> otherPrettyUsers = new ArrayList<PrettyPerson>();
        for (Person p1 : otherUsers){
            otherPrettyUsers.add(new PrettyPerson(p1.getId(),
                    p1.getUserId(),
                    //ToDo: find out why erroring out!
                    userService.getPerson(p1.getUserId()).get().getName(),
                    userService.getPerson(p1.getUserId()).get().getLastName(),
                    professionService.getProfession(Integer.toUnsignedLong(p1.getProfessionId())).get().name
                    ));
        }
        model.addObject("otherPrettyUsers", otherPrettyUsers);


        // ************************************** //
        // *********** User's Unfilled Request ************** //
        // ************************************** //

        List<Request> allRequests = requestService.getAllRequest();
        ArrayList<PrettyRequest> prettyRequestsList = new ArrayList<>();
        for (Request req : allRequests){
            if (req.getUserId() == thisUser.getId()){
                if (req.getFilled() == 0){
                    prettyRequestsList.add(requestService.requestToPrettyRequest(req));
                }
            }
        }
        Collections.reverse(prettyRequestsList);

        model.addObject("usersRequests",prettyRequestsList);


        // ************************************** //
        // *********** All Users Request ************** //
        // ************************************** //

        ArrayList<PrettyRequest> allPrettyRequests = new ArrayList<>();
        for (Request req : allRequests){
            allPrettyRequests.add(requestService.requestToPrettyRequest(req));
        }
        Collections.reverse(allPrettyRequests);

        model.addObject("allRequests", allPrettyRequests);

        model.setViewName("person/dashboard");
        return model;

    }

    @GetMapping("/profile")
    public ModelAndView getUserProfile(Authentication authentication, ModelAndView model){

        //get the current logged in person by
        //using the Authentication to get EMAIL,
        //and returning the user associated with the email

        Validate.notNull(authentication.getName(), "Garrett: When loading dashbaord, authentication has no name");
        User thisUser = userRepository.findByEmail(authentication.getName());
        Validate.notNull(thisUser, "Garrett: When loading the dashboard, could not find the user in the user repo!");
        //Log4J2LoggingSystem logger = new Log4J2LoggingSystem(ClassLoader.getSystemClassLoader());



        //Now that we have the user, we can get the userId
        //and perform a search in the personRepository to find out
        //if we have data for them

        Validate.notNull(thisUser.getId(), "Garrett: The found User Object has a null userid");
        Person thisPerson = personRepository.findByUserId(thisUser.getId());

        // DO NOT VALIDATE IF THEY ARE NOT NULL, WILL BE CREATED IN NEXT BLOCK!
        //Validate.notNull(thisUser.getId(), "Garrett: The current User obj's id is null");
        //Validate.notNull(thisPerson, "Garrett: Could not find Person based on User id");

        // ToDo: Make User class primary key a long!!

        if (thisPerson == null){
            model.addObject("personPassed", new Person());
            model.addObject("professionsList", professionService.getProfessions());
            model.setViewName("person/register");
            return model;
        }

        long thispersonId = thisUser.getId();
        personRepository.findByUserId(thispersonId);

        //Now that we may have a person, we can determine if they need to fill out info!
        model.addObject("thisuser",thisUser);
        model.addObject("person", thisPerson);
        model.addObject("profession",professionService.getProfession(Integer.toUnsignedLong(thisPerson.getProfessionId())).get());
        model.setViewName("person/profile");
        return model;

    }

    /**
     * Get Person
     *
     */
    @RequestMapping("/{id}")
    public Optional<Person> getPerson(@PathVariable("id") Long id) {
        return  personService.getPerson(id);
    }

    /**
     * Add Person
     */
    @RequestMapping(method= RequestMethod.POST, value="")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    /**
     * Update Person
     */
    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public void updatePerson(@RequestBody Person person, @PathVariable Long id){
        personService.updatePerson(id, person);
    }

    /**
     * Delete Person
     */
    @RequestMapping(method=RequestMethod.DELETE,value="")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

}
