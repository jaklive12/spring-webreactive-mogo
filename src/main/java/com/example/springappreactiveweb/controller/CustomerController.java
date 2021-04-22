package com.example.springappreactiveweb.controller;

import com.example.springappreactiveweb.entity.Customer;
import com.example.springappreactiveweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Customer> getAllCustomers() {
        return service.getCustomers();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> getCustomer(@PathVariable String id) {
        return service.getCustomerById(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(params = "name")
    public Mono<ResponseEntity<Customer>> getCustomerByName(@RequestParam("name") String name) {
        return service.getCustomerByFirstName(name).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Customer> postCustomer(@RequestBody Mono<Customer> customer) {
        return service.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable String id, @RequestBody Mono<Customer> customer) {
        return service.updateCustomerById(id, customer).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id) {
        return service.deleteCustomer(id);
    }
}