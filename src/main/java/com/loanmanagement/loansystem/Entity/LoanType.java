package com.loanmanagement.loansystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_Type_Id;

    @Column(name="loan_type")
    private String loanType;
    
    @Column(name="interest_rate")
    private Double interestRate;

	/*
	 * @OneToMany(mappedBy = "loanTypeEntity") private Set<LoanApplication>
	 * loanApplications;
	 */

	public Long getLoan_Type_Id() {
		return loan_Type_Id;
	}

	public void setLoan_Type_Id(Long loan_Type_Id) {
		this.loan_Type_Id = loan_Type_Id;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

    
}
