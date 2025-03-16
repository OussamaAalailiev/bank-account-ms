package com.example.web;

import com.example.entities.BankAccount;
import com.example.feignClient.CustomerFeignClient;
import com.example.model.Customer;
import com.example.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/accounts")
public class BankAccountRestController {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerFeignClient customerFeignClient;

    public BankAccountRestController(BankAccountRepository bankAccountRepository, CustomerFeignClient customerFeignClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerFeignClient = customerFeignClient;
    }

    @GetMapping
    public List<BankAccount> getBankAccounts() {
        List<BankAccount> bankAccounts = this.bankAccountRepository.findAll();
        bankAccounts.forEach(bankAccount -> {
            bankAccount.setCustomer(customerFeignClient.getOneById(bankAccount.getCustomerId()));
        });
        return bankAccounts;
    }

    @GetMapping(path = "/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        BankAccount bankAccount = this.bankAccountRepository.findById(id).get();
        Customer customer = this.customerFeignClient.getOneById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

}
