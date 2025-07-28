package com.loanmanagement.loansystem.Service;

import com.loanmanagement.loansystem.DTOs.AdminReport;
import com.loanmanagement.loansystem.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportSvcImpl implements ReportService {
    @Autowired
    private LoanAppRepository loanAppRepository;

    @Autowired
    private CustomerDetailRepository customerDetailRepository; // Assuming this exists for customer count

    @Autowired
    private BankDetailsRepository bankDetailsRepository; // Assuming this exists for bank count

    @Autowired
    private UserRepository userRepository; // For total users (customers + admins)

    @Override
    public AdminReport generateAdminReport() {
        AdminReport report = new AdminReport();

        report.setTotalLoans(loanAppRepository.count());
        report.setApprovedLoans(loanAppRepository.countByStatus("Approved"));
        report.setPendingLoans(loanAppRepository.countByStatus("Pending"));
        report.setRejectedLoans(loanAppRepository.countByStatus("Reject"));

        report.setTotalCustomers(userRepository.count()); // Assuming all users are customers for this report
        report.setTotalBanks(bankDetailsRepository.count()); // Assuming you have a BankDetails entity and repository

        return report;
    }
}
