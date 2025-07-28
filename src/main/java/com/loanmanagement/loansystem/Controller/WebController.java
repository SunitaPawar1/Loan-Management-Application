package com.loanmanagement.loansystem.Controller;

import com.loanmanagement.loansystem.Entity.LoanApplication;
import com.loanmanagement.loansystem.Entity.Role;
import com.loanmanagement.loansystem.Entity.User;
import com.loanmanagement.loansystem.Exception.NoLoanHistoryFoundException;
import com.loanmanagement.loansystem.Repository.LoanAppRepository;
import com.loanmanagement.loansystem.Repository.RoleRepository;
import com.loanmanagement.loansystem.Repository.UserRepository;
import com.loanmanagement.loansystem.Security.LoanAppUserDetails;
import com.loanmanagement.loansystem.Service.LoanService;
import com.loanmanagement.loansystem.Service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private LoanService loanService;
	
	@Autowired
    private RoleRepository roleRepository;

	@Autowired
    private LoanAppRepository loanAppRepository;

    // Home page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Show registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll()); // to populate dropdown
        return "register";
    }

    // Handle registration
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam("selectedRole") String selectedRole) {
        userService.registerUser(user, selectedRole);
        return "redirect:/login";
    }


    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Spring Security handles POST
    }

    @GetMapping("/default")
    public String redirectBasedOnRole(Authentication authentication) {
        if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin-dashboard";
        } else if (authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_CUSTOMER"))) {
            return "redirect:/customer-dashboard";
        } else {
            return "redirect:/login?error=unauthorized";
        }
    }
    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard"; // must match HTML name
    }
    @GetMapping("/customer-dashboard")
    public String customerDashboard() {
        return "customer-dashboard";
    }
    
    @PostMapping("/loanApplication")
    public String showLoanAppForm(@ModelAttribute("loanapplication") LoanApplication loanApp, Authentication authentication) {
    	//User user= loanAppUserDetails.getUser();
    	String username = authentication.getName();
    	User user= userService.findByUsername(username);
    	//loanApp.setUserId(((User) authentication.getPrincipal()).getId());
    	
    	loanApp.setUserId(user.getId());
    	loanApp.setApplied_on(LocalDate.now());
    	loanApp.setStatus("Pending");
    	loanAppRepository.save(loanApp);
    	return "/loanApplication";
    }
    
    @GetMapping("/loan-history")
    public ResponseEntity< List<LoanApplication>> getLoanHistory(Authentication authentication) {
    	try {
	        String username = authentication.getName();
	        List<LoanApplication> loanApplication = loanService.findByLoanappid(username);
	        return ResponseEntity.ok(loanApplication);
    	}
    	catch (NoLoanHistoryFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }
      
    @GetMapping("/userprofile")
    public ResponseEntity<User> getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/loan-details")
    public ResponseEntity< List<LoanApplication>> getLoanDetailsForApproval(Authentication authentication) {
        try {
            String username = authentication.getName();
            List<LoanApplication> loanApplication = loanService.findByLoanappid(username);
            return ResponseEntity.ok(loanApplication);
        }
        catch (NoLoanHistoryFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }
}
