package com.example.springappreactiveweb.repository;

import com.example.springappreactiveweb.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {
    Mono<Customer> findByFirstName(String fistName);
}
