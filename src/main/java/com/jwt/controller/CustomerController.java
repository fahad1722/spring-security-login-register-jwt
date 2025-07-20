package com.jwt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.dto.LoginRequest;
import com.jwt.entity.Customer;
import com.jwt.service.CustomerService;
import com.jwt.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        log.info("Registration attempt for email: {}", customer.getEmail());
        try {
            boolean isSaved = customerService.saveCustomer(customer);
            if (isSaved) {
                log.info("Customer registered successfully for email: {}", customer.getEmail());
                return ResponseEntity.ok("Customer registered successfully");
            } else {
                log.error("Registration failed for email: {}", customer.getEmail());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
            }
        } catch (Exception e) {
            log.error("Registration failed for email: {} due to: {}", customer.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody LoginRequest loginRequest) {
        log.info("Login attempt for email: {}", loginRequest.getEmail());
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            boolean isAuthenticated = authentication.isAuthenticated();
            if (isAuthenticated) {
                String jwtToken = jwtUtil.generateToken(loginRequest.getEmail());
                log.info("Login successful for email: {}", loginRequest.getEmail());
                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
            } else {
                log.warn("Authentication failed for email: {}", loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }
        } catch (Exception e) {
            log.error("Login failed for email: {}. Reason: {}", loginRequest.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        log.info("Fetching profile for email: {}", email);
        Customer customer = customerService.findByEmail(email);
        return ResponseEntity
                .ok(Map.of("name", customer.getName(), "email", customer.getEmail(), "role", customer.getRole()));
    }

    @GetMapping("/admin/data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getAdminData() {
        log.info("Access granted to /admin/data");
        return ResponseEntity.ok("Sensitive admin data");
    }

    @GetMapping("/customer/data")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> getUserData() {
        log.info("Access granted to /customer/data");
        return ResponseEntity.ok("Customer data");
    }

    @GetMapping("/admin/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllCustomers() {
        log.info("Fetching all registered customers");
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (Exception e) {
            log.error("Error retrieving customers: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error retrieving customers: " + e.getMessage());
        }
    }
}
