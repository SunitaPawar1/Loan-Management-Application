package com.loanmanagement.loansystem.Controller;

import com.loanmanagement.loansystem.Entity.User;
import com.loanmanagement.loansystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class WebController {

    @Autowired
    private UserRepository userRepository;

    // Home Page
    @GetMapping("/")
    public String home() {
        return "index"; // returns index.html
    }

    // Registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Save user
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        user.setStatus("ACTIVE");
        userRepository.save(user);
        return "redirect:/login";
    }

    // Login form (no authentication yet)
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
