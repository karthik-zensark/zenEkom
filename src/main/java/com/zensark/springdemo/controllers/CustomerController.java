package com.zensark.springdemo.controllers;

import com.zensark.springdemo.entities.Customer;
import com.zensark.springdemo.repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Optional<Customer> customer = repository.findById(id);
        if(customer.isPresent()){
            return ResponseEntity.ok(customer.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        repository.findAll().forEach(customer -> customers.add(customer));
        return customers;
    }

    @PostMapping("/create")
    Customer createUser(@RequestBody Customer customer){
        return repository.save(customer);
    }
}
