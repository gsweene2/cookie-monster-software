package com.garrett.firstwebsite.Request;

import com.garrett.firstwebsite.item.ItemService;
import com.garrett.firstwebsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    /**
     * Dependency Injection of Request Repository, singleton.
     * Note that when the framework sees apache Derby database in the classpath,
     * it uses that database. No further connection needed!
     */

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    private List<Request> requests = new ArrayList<>();

    public List<Request> getAllRequest() {
        //return requests
        //
        List<Request> requests = new ArrayList<>();
        // Lambda
        requestRepository.findAll().forEach(requests::add);
        // return the list
        return requests;
    }

    public Optional<Request> getRequest(long id) {
        //return transactions
        return requestRepository.findById(id);
    }

    public void addRequest(Request request){
        requestRepository.save(request);
    }

    public void updateRequest(long id, Request request){
        requestRepository.save(request);
    }

    public void deleteRequest(long id) { requestRepository.deleteById(id);}

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
            fulfillerFirstName = userService.getPerson(request.getUserId()).get().getName();
            fulfillerLastName = userService.getPerson(request.getUserId()).get().getLastName();
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
}
