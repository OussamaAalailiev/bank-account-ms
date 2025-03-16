package com.example.feignClient;

import com.example.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// This Microservice (MS) 'ACCOUNT-SERVICE' will talk to the MS named 'CUSTOMER-SERVICE' through this declarative interface with the help of 'OpenFeign',
//   we just specify the methods that we want to get from the MS (methods names here does not matter):
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerFeignClient {
    @GetMapping(path = "/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer getOneById(@PathVariable Long id);

    @GetMapping(path = "/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getEmptyList")
    List<Customer> getAllCustomers();

    // Automatically receives an Exception through 'CircuitBreaker':
    default Customer getDefaultCustomer(Long id, Exception exception) {
        Customer defaultCustomer = new Customer();
        defaultCustomer.setId(id);
        defaultCustomer.setFirstName("First name is unavailable");
        defaultCustomer.setLastName("Last name is unavailable");
        defaultCustomer.setEmail("Email is unavailable");
        return defaultCustomer;
    }

    default List<Customer> getEmptyList(Exception exception) {
        return List.of();// Return empty list.
    }

}
