package com.example.blink.services;

import com.example.blink.models.User;
import com.example.blink.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(Long.parseLong(userId)).orElse(null);

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
