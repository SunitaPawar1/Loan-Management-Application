package com.loanmanagement.loansystem.Entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_application")
public class LoanApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="loan_app_id")
	private Long loanappid;
	
	@Column(name="user_id")
	private Long userId;
	
	private Long loan_type_id;
	private double loan_amount;
	private int tenure;
	private String status;
	private LocalDate applied_on;
	private LocalDate approved_on;
	private String remarks;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "loan_type_id", nullable = false) private LoanType
	 * loanTypeEntity;
	 */
	
	public Long getLoanappid() {
		return loanappid;
	}
	public void setLoanappid(Long loanappid) {
		this.loanappid = loanappid;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getLoan_type_id() {
		return loan_type_id;
	}
	public void setLoan_type_id(Long loan_type_id) {
		this.loan_type_id = loan_type_id;
	}
	public double getLoan_amount() {
		return loan_amount;
	}
	public void setLoan_amount(double loan_amount) {
		this.loan_amount = loan_amount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getApplied_on() {
		return applied_on;
	}
	public void setApplied_on(LocalDate applied_on) {
		this.applied_on = applied_on;
	}
	public LocalDate getApproved_on() {
		return approved_on;
	}
	public void setApproved_on(LocalDate approved_on) {
		this.approved_on = approved_on;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
