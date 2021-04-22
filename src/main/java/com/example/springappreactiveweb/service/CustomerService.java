package com.example.springappreactiveweb.service;

import com.example.springappreactiveweb.entity.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<Customer> getCustomers();

    Mono<Customer> getCustomerById(String id);

    Mono<Customer> getCustomerByFirstName(String firstName);

    Mono<Customer> createCustomer(Mono<Customer> customer);

    Mono<Customer> updateCustomerById(String id, Mono<Customer> customer);

    Mono<Void> deleteCustomer(String id);
}
