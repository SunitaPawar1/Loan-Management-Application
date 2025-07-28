package com.loanmanagement.loansystem.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.loanmanagement.loansystem.Entity.LoanApplication;
import com.loanmanagement.loansystem.Entity.LoanCalculation;
import com.loanmanagement.loansystem.Entity.User;
import com.loanmanagement.loansystem.Exception.NoLoanHistoryFoundException;
import com.loanmanagement.loansystem.Exception.ResourceNotFoundException;
import com.loanmanagement.loansystem.Repository.LoanAppRepository;
import com.loanmanagement.loansystem.Repository.LoanCalculationRepository;
import com.loanmanagement.loansystem.Repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
	@Autowired
	private LoanAppRepository loanAppRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public List<LoanApplication> findByLoanappid(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		List<LoanApplication> loanApplicationList = new ArrayList<>();

		if(user.isPresent())
			loanApplicationList = loanAppRepository.findByUserId(user.get().getId());

		if (loanApplicationList.isEmpty()) {
			throw new NoLoanHistoryFoundException("No loan history found for user : " + username);
		}

		return loanApplicationList;
	}

	// 1. Get all pending loan applications
	public List<LoanApplication> getAllPendingLoans(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		List<LoanApplication> loanApplicationList = new ArrayList<>();

		if(user.isPresent())
			loanApplicationList = loanAppRepository.findByStatus("Pending");
		return loanApplicationList;
	}

	// 2. Get all approved loan applications
	public List<LoanApplication> getApprovedLoans() {
		return loanAppRepository.findByStatus("Approved");
	}

	// 3. Get all rejected loan applications
	public List<LoanApplication> getRejectedLoans() {
		return loanAppRepository.findByStatus("Reject");
	}

	// 4. Approve/Reject a specific loan
	public boolean updateLoanStatus(Long loanId, String status) {
		LoanApplication loan = loanAppRepository.findById(loanId)
				.orElseThrow(() -> new ResourceNotFoundException("Loan ID not found: " + loanId));

		loan.setStatus(status);
		loanAppRepository.save(loan);
		return true;
	}
}
