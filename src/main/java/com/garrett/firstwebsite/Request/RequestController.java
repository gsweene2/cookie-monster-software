package com.garrett.firstwebsite.Request;

import com.garrett.firstwebsite.item.ItemService;
import com.garrett.firstwebsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
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

    /**
     * Get all transactions
     */
    @RequestMapping("")
    public String getAllTransactions(Model model) {
        model.addAttribute("allRequests", requestService.getAllRequest());
        return "request/allRequests";
    }

    @RequestMapping("/create")
    public String createRequestForm(Model model){
        model.addAttribute("possibleItems", itemService.getAllItem());
        model.addAttribute("requestPassed", new Request());
        return "request/create";
    }

    @PostMapping("/create")
    public String createRequestResult(@ModelAttribute Request request, Authentication authentication){
        request.setDateRequested(LocalDate.now(ZoneId.of("America/Montreal")));
        request.setUserId(userRepository.findByEmail(authentication.getName()).getId());
        request.setFilled(0);

        requestService.addRequest(request);
        return "request/result";
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
    @RequestMapping(method=RequestMethod.DELETE,value="")
    public void deleteTransaction(@PathVariable long id) {
        requestService.deleteRequest(id);
    }

}
