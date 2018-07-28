package com.garrett.firstwebsite.Request;

import com.garrett.firstwebsite.item.ItemService;
import com.garrett.firstwebsite.person.Person;
import com.garrett.firstwebsite.person.PersonController;
import com.garrett.firstwebsite.user.User;
import com.garrett.firstwebsite.user.UserRepository;
import com.garrett.firstwebsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.jws.WebParam;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/request")
public class RequestController {

    /**
     * Dependency Injection of UserService, singleton.
     */
    @Autowired
    RequestService requestService;

    @Autowired
    ItemService itemService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PersonController personController;

    /**
     * Get all transactions
     */
    @RequestMapping("")
    public String getAllTransactions(Model model) {
        List<Request> allRequests = requestService.getAllRequest();
        ArrayList<PrettyRequest> allPrettyRequests = new ArrayList<>();
        for (Request request : allRequests){
            allPrettyRequests.add(requestService.requestToPrettyRequest(request));
        }
        model.addAttribute("allRequests", allPrettyRequests);
        return "request/allRequests";
    }

    @RequestMapping("/create")
    public String createRequestForm(Model model){
        model.addAttribute("possibleItems", itemService.getAllItem());
        model.addAttribute("requestPassed", new Request());
        return "request/create";
    }

    @PostMapping("/create")
    public ModelAndView createRequestResult(@ModelAttribute Request request, Authentication authentication){
        request.setDateRequested(LocalDate.now(ZoneId.of("America/Montreal")));
        request.setUserId(userRepository.findByEmail(authentication.getName()).getId());
        request.setFilled(0);
        requestService.addRequest(request);
        PrettyRequest prettyRequest = requestToPrettyRequest(request);
        ModelAndView model = new ModelAndView();
        model.addObject("prettyRequest", prettyRequest);
        model.setViewName("request/result");
        return model;
    }

    @GetMapping("/fulfill/{id}")
    public ModelAndView promptFulfillRequest(@PathVariable("id") Long id){
        // Need to pass the request and a button to fulfill it
        Request thisReq = requestService.getRequest(id).get();
        PrettyRequest thisPrettyReq = requestService.requestToPrettyRequest(thisReq);
        ModelAndView model = new ModelAndView();
        model.addObject("prettyRequest", thisPrettyReq);
        model.setViewName("request/approve");
        return model;
    }

    @PostMapping("/fulfill/{id}")
    public RedirectView fulfillRequest(@PathVariable("id") Long id, Authentication authentication){

        // Get Request
        Request request = requestService.getRequest(id).get();
        // determine the fulfiller
        User thisUser = userRepository.findByEmail(authentication.getName());
        // Update the request
        request.setFilled(1);
        request.setFillerId(thisUser.getId());
        request.setDateFilled(LocalDate.now(ZoneId.of("America/Montreal")));
        // Save request as filled with new person
        requestService.updateRequest(id, request);


        // Return Dashboard
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/person/allRequests");
        return redirectView;

    }

    public PrettyRequest requestToPrettyRequest(Request request){

        String dateFilled;
        String fulfillerFirstName;
        String fulfillerLastName;
        String filledStatus;

        if (request.getFilled() == 0){
            fulfillerFirstName = "";
            fulfillerLastName = "";
            dateFilled = "";
            filledStatus = "Unfilled";
        } else {
            fulfillerFirstName = userService.getPerson(request.getFillerId()).get().getName();
            fulfillerLastName = userService.getPerson(request.getFillerId()).get().getLastName();
            dateFilled = request.getDateFilled().toString();
            filledStatus = "Filled";
        }

        PrettyRequest prettyRequest = new PrettyRequest(
                request.getId(),
                itemService.getItem(request.getItemId()).get().getName(),
                itemService.getItem(request.getItemId()).get().getAmount(),
                userService.getPerson(request.getUserId()).get().getName(),
                userService.getPerson(request.getUserId()).get().getLastName(),
                request.getDateRequested().toString(),
                dateFilled,
                fulfillerFirstName,
                fulfillerLastName,
                filledStatus);
        return prettyRequest;
    }

    /**
     * Get Request
     *
     */
    @RequestMapping("/{id}")
    public Optional<Request> getTransaction(@PathVariable("id") long id) {
        return  requestService.getRequest(id);
    }

    /**
     * Add Request
     */
    @RequestMapping(method= RequestMethod.POST, value="")
    public void addTransaction(@RequestBody Request request) {
        requestService.addRequest(request);
    }

    /**
     * Update Request
     */
    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public void updateTransaction(@RequestBody Request request, @PathVariable long id){
        requestService.updateRequest(id, request);
    }

    /**
     * Delete Request
     */
    @RequestMapping(method=RequestMethod.GET,value="/delete/{id}")
    public RedirectView deleteTransaction(@PathVariable long id, Authentication authentication) {
        requestService.deleteRequest(id);

        // Return to dashboard
        /*
        ModelAndView model = new ModelAndView();
        //model.setViewName("person/dashboard");
        model = personController.getUserDashboard(authentication,model);
        return model;
        */
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/person/allRequests");
        return redirectView;
    }

}
