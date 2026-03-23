package com.moolgranth.backend.controller;

import com.moolgranth.backend.dto.LoginRequest;
import com.moolgranth.backend.dto.RegisterRequest;
import com.moolgranth.backend.entity.User;
import com.moolgranth.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"https://moolgranth.com", "https://www.moolgranth.com", "http://localhost:5173"}, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        // Check if phone already exists
        if (userRepository.findByPhone(request.getPhone()).isPresent()) {
            return ResponseEntity.badRequest().body("Phone number already registered.");
        }

        // Create new user
        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setPhone(request.getPhone());
        newUser.setPassword(request.getPassword()); // In a real app, we would hash this!
        newUser.setEmail(request.getEmail());
        newUser.setRole("customer");

        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        
        // Admin Login
        if (request.isAdmin()) {
            if ("admin123".equals(request.getSecretKey())) {
                User admin = new User();
                admin.setName("Super Admin");
                admin.setRole("admin");
                return ResponseEntity.ok(admin);
            }
            return ResponseEntity.status(401).body("Invalid Admin Secret Key!");
        }

        // Customer Login
        Optional<User> userOpt = userRepository.findByPhone(request.getPhone());
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Checking raw password for local testing
            if (user.getPassword().equals(request.getPassword())) {
                return ResponseEntity.ok(user);
            }
        }
        
        return ResponseEntity.status(401).body("Invalid phone number or password.");
    }
}