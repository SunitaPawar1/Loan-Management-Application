package com.loanmanagement.loansystem.Controller;

import com.loanmanagement.loansystem.DTOs.AdminReport;
import com.loanmanagement.loansystem.Entity.LoanApplication;
import com.loanmanagement.loansystem.Entity.LoanCalculation;
import com.loanmanagement.loansystem.Exception.NoLoanHistoryFoundException;
import com.loanmanagement.loansystem.Service.LoanService;
import com.loanmanagement.loansystem.Service.ReportService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    @Autowired
    private LoanService loanService;
    @Autowired
    private ReportService reportService;
    @GetMapping("/loans/pending")
    public String viewPendingLoans(Model model, Authentication authentication) {
        try {
            String username = authentication.getName();
            List<LoanApplication> pendingLoans = loanService.getAllPendingLoans(username);
            // Add pending loans to the model
            model.addAttribute("pendingLoans", pendingLoans);

            // Return the view name
            return "pendingLoans";
        }
        catch (NoLoanHistoryFoundException ex) {
            return "error";
        }
    }

    @GetMapping("/loans/approved")
    public String viewApprovedLoans(Model model) {
        List<LoanApplication> approvedLoans = loanService.getApprovedLoans();
        model.addAttribute("approvedLoans", approvedLoans);
        return "loan-approved"; // This HTML will display approved loans
    }
    @GetMapping("/loans/rejected")
    public String viewRejectedLoans(Model model) {
        List<LoanApplication> rejectedLoans = loanService.getRejectedLoans();
        model.addAttribute("rejectedLoans", rejectedLoans);
        return "loan-rejected"; // This HTML will display rejected loans
    }

    // Approve loan request
    @PostMapping("/loan/{loanId}/approve")
    public String approveLoan(@PathVariable Long loanId,  RedirectAttributes redirectAttributes) {
        boolean success = loanService.updateLoanStatus(loanId, "Approved");
        if (!success) {
            redirectAttributes.addFlashAttribute("errorMessage", "Loan not found!");
        }
        return "redirect:/admin/loans/pending";
    }

    // Reject loan request
    @PostMapping("/loan/{loanId}/reject")
    public String rejectLoan(@PathVariable Long loanId, RedirectAttributes redirectAttributes) {
        boolean success = loanService.updateLoanStatus(loanId, "Reject");
        if (!success) {
            redirectAttributes.addFlashAttribute("errorMessage", "Loan not found!");
        }
        return "redirect:/admin/loans/pending";
    }
    @GetMapping("/reports")
    public String generateReports(Model model) {
        AdminReport report = reportService.generateAdminReport();
        model.addAttribute("report", report);
        return "report"; // This HTML will display the report
    }
}

