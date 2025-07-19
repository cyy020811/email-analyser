package com.email.analyser.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.email.analyser.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void showUser(String id) {
        var user = userRepository.findById(UUID.fromString(id));
        System.out.println(user);
    }
}
