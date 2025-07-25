package com.loanmanagement.loansystem.Security;

import com.loanmanagement.loansystem.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class LoanAppUserDetails implements UserDetails {

    private final User user;

    public LoanAppUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()))
                .collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can customize this if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You can customize this if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can customize this if needed
    }

    @Override
    public boolean isEnabled() {
        return "ACTIVE".equalsIgnoreCase(user.getStatus());
    }

    public User getUser() {
        return user;
    }
}
