package com.loanmanagement.loansystem.Service;

import com.loanmanagement.loansystem.Entity.Role;
import com.loanmanagement.loansystem.Entity.User;
import com.loanmanagement.loansystem.Repository.RoleRepository;
import com.loanmanagement.loansystem.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(User user, String selectedRole) {
        user.setStatus("ACTIVE");
        user.setCreatedAt(LocalDateTime.now());

        // Password encoding
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Role assignment
        Role role = roleRepository.findByRoleName(selectedRole.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Role not found: " + selectedRole));
        user.setRoles(Set.of(role));

        userRepository.save(user);
    }
}