package com.loanmanagement.loansystem.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "BankDetails")
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @Column(nullable = false, unique = true)
    private String ifscCode;

    @Column(nullable = false)
    private String branch;

    // One bank has multiple customers
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private Set<CustomerDetail> customers;

    // Constructors, getters and setters

    public BankDetails() {}

    // Getters and setters omitted for brevity
}

