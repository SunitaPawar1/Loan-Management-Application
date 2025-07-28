package com.loanmanagement.loansystem.DTOs;

import lombok.Data;

@Data
public class AdminReport {
    private long totalLoans;
    private long approvedLoans;
    private long pendingLoans;
    private long rejectedLoans;
    private long totalCustomers;
    private long totalBanks;

    // Getters and Setters

    // Optional: toString() for logging
}

