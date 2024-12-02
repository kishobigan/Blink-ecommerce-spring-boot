package com.example.blink.controllers;

import com.example.blink.models.User;
import com.example.blink.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get-user")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        String userId = request.getAttribute("userId").toString();
        User user = userService.getUserByUserId(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/list-users")
    public ResponseEntity<?> listUsers(HttpServletRequest request) {
        String role = request.getAttribute("role").toString();
        if (role.equals("admin")) {
            return ResponseEntity.ok(userService.getAllUsers());
        }
        return ResponseEntity.badRequest().build();
    }
}
