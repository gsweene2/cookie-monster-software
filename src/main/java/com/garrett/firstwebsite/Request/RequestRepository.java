package com.garrett.firstwebsite.Request;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findByUserId(Long userId);
}
