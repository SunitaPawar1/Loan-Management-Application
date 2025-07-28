package com.loanmanagement.loansystem.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "LoanCalculation")
public class LoanCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(nullable = false)
    private Double principalAmount;

    @Column(nullable = false)
    private Integer duration; // in months

    @Column(nullable = false)
    private Double interestRate;

    @Column(nullable = false)
    private String loanType; // For example: "Home", "Car", etc.

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long bankId;

    @Column(nullable = false)
    private String status; // Requested, Approved, Rejected

    private LocalDate applicationDate;

    private LocalDate approvalDate;

    // Optional: Additional fields like EMI, Collateral status
    private Double emiAmount;

    private Boolean collateral;

    // Constructors
    public LoanCalculation() {}

    public LoanCalculation(Double principalAmount, Integer duration, Double interestRate,
                           String loanType, Long customerId, Long bankId, String status,
                           LocalDate applicationDate, Boolean collateral) {
        this.principalAmount = principalAmount;
        this.duration = duration;
        this.interestRate = interestRate;
        this.loanType = loanType;
        this.customerId = customerId;
        this.bankId = bankId;
        this.status = status;
        this.applicationDate = applicationDate;
        this.collateral = collateral;
    }

    // Getters and Setters

    public Long getLoanId() { return loanId; }
    public void setLoanId(Long loanId) { this.loanId = loanId; }

    public Double getPrincipalAmount() { return principalAmount; }
    public void setPrincipalAmount(Double principalAmount) { this.principalAmount = principalAmount; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Double getInterestRate() { return interestRate; }
    public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }

    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getBankId() { return bankId; }
    public void setBankId(Long bankId) { this.bankId = bankId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }

    public LocalDate getApprovalDate() { return approvalDate; }
    public void setApprovalDate(LocalDate approvalDate) { this.approvalDate = approvalDate; }

    public Double getEmiAmount() { return emiAmount; }
    public void setEmiAmount(Double emiAmount) { this.emiAmount = emiAmount; }

    public Boolean getCollateral() { return collateral; }
    public void setCollateral(Boolean collateral) { this.collateral = collateral; }
}

