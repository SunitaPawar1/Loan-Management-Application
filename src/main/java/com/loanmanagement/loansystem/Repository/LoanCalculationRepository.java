package com.loanmanagement.loansystem.Repository;

import com.loanmanagement.loansystem.Entity.LoanCalculation;
import org.springframework.data.jpa.repository.JpaRepository; // Changed from CrudRepository
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanCalculationRepository extends JpaRepository<LoanCalculation, Long> {
    List<LoanCalculation> findByStatus(String status); // Renamed parameter for clarity

    long countByStatus(String status);
}
