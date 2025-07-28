package com.loanmanagement.loansystem.Repository;

import com.loanmanagement.loansystem.Entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long> {
}
