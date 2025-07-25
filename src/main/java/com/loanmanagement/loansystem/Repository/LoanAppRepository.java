package com.loanmanagement.loansystem.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanmanagement.loansystem.Entity.LoanApplication;

public interface LoanAppRepository extends JpaRepository<LoanApplication, Long>{
	Optional<LoanApplication> findByLoanappid(Long loanappid);
	List<LoanApplication> findByUserId(Long userId);
}


