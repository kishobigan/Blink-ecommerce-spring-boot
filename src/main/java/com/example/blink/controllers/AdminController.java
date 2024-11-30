package com.example.blink.controllers;

import com.example.blink.dto.CreateAdminRequest;
import com.example.blink.repositories.AdminRepository;
import com.example.blink.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/auth/createAdmin")
    public ResponseEntity<String> createAdmin(@RequestBody CreateAdminRequest adminRequest) {
        adminService.createAdmin(adminRequest);
        return ResponseEntity.ok("Admin created");
    }
}
