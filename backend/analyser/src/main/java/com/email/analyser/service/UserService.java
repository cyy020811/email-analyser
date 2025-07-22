package com.email.analyser.service;

import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.email.analyser.dto.UserDto;
import com.email.analyser.repository.UserRepository;
import com.email.analyser.mapper.UserMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Iterable<UserDto> getAllUsers() {

        return userRepository.findAll(Sort.by("lastSignInAt"))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getUser(UUID id) {
        var user = userRepository.findById(id).orElseThrow();
        return userMapper.toDto(user);
    }

    public void deleteUser(UUID id) {
        var user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }
}
