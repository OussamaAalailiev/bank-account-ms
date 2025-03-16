package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
