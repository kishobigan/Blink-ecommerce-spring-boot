package com.example.blink.controllers;

import com.example.blink.dto.RegisterUserRequest;
import com.example.blink.models.User;
import com.example.blink.repositories.UserRepository;
import com.example.blink.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        if (userRepository.findByEmail(registerUserRequest.getEmail()).isPresent()){
            return ResponseEntity.status(409).body("Email already exists");
        }
        User newUser = new User();
        newUser.setEmail(
                registerUserRequest.getEmail()
        );
        String encodedPassword = passwordEncoder.encode(registerUserRequest.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setFirstName(registerUserRequest.getFirstName());
        newUser.setLastName(registerUserRequest.getLastName());
        newUser.setAddress(registerUserRequest.getAddress());
        newUser.setPhoneNumber(registerUserRequest.getPhoneNumber());
        userRepository.save(newUser);
        return ResponseEntity.status(201).body("User registered successfully");
    }
}
