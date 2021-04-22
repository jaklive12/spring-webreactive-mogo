package com.example.springappreactiveweb.service;

import com.example.springappreactiveweb.entity.Customer;
import com.example.springappreactiveweb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    public Flux<Customer> getCustomers() {
        return repository.findAll();
    }

    public Mono<Customer> getCustomerById(String id) {
        return repository.findById(id);
    }

    public Mono<Customer> getCustomerByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    public Mono<Customer> createCustomer(Mono<Customer> customer) {
        return customer.flatMap(c -> repository.save(c));
    }

    public Mono<Customer> updateCustomerById(String id, Mono<Customer> customer) {
        return customer.flatMap(c -> repository.findById(id).flatMap(s -> {
            c.setId(s.getId());
            return repository.save(c);
        }));
    }

    public Mono<Void> deleteCustomer(String id) {
        return repository.deleteById(id);
    }
}
