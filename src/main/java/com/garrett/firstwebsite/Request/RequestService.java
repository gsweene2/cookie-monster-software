package com.garrett.firstwebsite.Request;

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
}
