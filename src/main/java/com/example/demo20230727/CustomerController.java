package com.example.demo20230727;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping("/{id}")
    public Customer read(@PathVariable String id) {
        Customer customer = repository.findById(id).get();
        return customer;
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        customer.setId(UUID.randomUUID().toString());
        customer = repository.save(customer);
        return customer;
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable String id, @RequestBody Customer customer) {
        Customer existingCustomer = repository.findById(id).get();
        existingCustomer.setName(customer.getName());
        existingCustomer.setPhone(customer.getPhone());

        repository.save(existingCustomer);
        return existingCustomer;
    }

    @DeleteMapping
    public String delete() {
        return "Delete Customer Info";
    }

}
