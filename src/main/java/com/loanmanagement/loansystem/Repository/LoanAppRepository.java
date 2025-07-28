package com.loanmanagement.loansystem.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanmanagement.loansystem.Entity.LoanApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanAppRepository extends JpaRepository<LoanApplication, Long>{
	List<LoanApplication> findByUserId(Long userId);
	List<LoanApplication> findByStatus(String status);
	Long countByStatus(@Param("status") String status);
}


