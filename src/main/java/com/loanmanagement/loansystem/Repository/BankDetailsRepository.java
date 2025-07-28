package com.loanmanagement.loansystem.Repository;

import com.loanmanagement.loansystem.Entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
}
