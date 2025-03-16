package com.example.web;

import com.example.entities.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerRestController {
    private final CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(path = "")
    List<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    Customer getCustomer(@PathVariable Long id) {
        return this.customerRepository.findById(id).get();
    }

}
