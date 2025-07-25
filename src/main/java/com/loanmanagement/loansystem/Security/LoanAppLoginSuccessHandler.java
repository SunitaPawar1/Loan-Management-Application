package com.loanmanagement.loansystem.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Slf4j
@Component
public class LoanAppLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

       // log.info("Login successful for user: {}", authentication.getName());

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            String role = auth.getAuthority();

            if ("ROLE_ADMIN".equals(role)) {
                response.sendRedirect("/admin-dashboard");
                return;
            } else if ("ROLE_CUSTOMER".equals(role)) {
                response.sendRedirect("/customer-dashboard");
                return;
            }
        }

        // Default fallback
        response.sendRedirect("/default-dashboard");
    }
}

