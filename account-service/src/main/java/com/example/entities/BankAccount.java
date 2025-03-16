package com.example.entities;

import com.example.enums.AccountType;
import com.example.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
@Entity
public class BankAccount {
    @Id
    private String id;
    private Double balance;
    private String currency;
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    //Customer n'est pas presenter ici dans la base, car elle exist dans un autre microservice.
    @Transient
    private Customer customer;
    private Long customerId; //pour mapper la relation manuellement.
}
