package com.example.blink.services;

import com.example.blink.dto.CreateAdminRequest;
import com.example.blink.models.Admin;
import com.example.blink.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(CreateAdminRequest createAdminRequest) {
        Admin admin = new Admin();
        admin.setAdminName(createAdminRequest.getAdminName());
        admin.setPassword(passwordEncoder.encode(createAdminRequest.getPassword()));
        admin.setEmail(createAdminRequest.getEmail());
        return adminRepository.save(admin);
    }
}
