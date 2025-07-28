package com.loanmanagement.loansystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CustomerDetail")
@Data
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String custName;

    @Column(nullable = false)
    private Double income;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 15)
    private String mobile;

    @Column(nullable = false, unique = true)
    private String PAN;

    @Column(nullable = false)
    private Integer cibilScore;

    // Many customers belong to one bank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankId", nullable = false)
    private BankDetails bank;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String pass;

    // Authorization - for roles like customer, employee etc. You may map to an Auth entity or simple string
    @Column(nullable = false)
    private Long authId;

    // If single loan linkage needed
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loanId")
    private LoanCalculation loan;
}
