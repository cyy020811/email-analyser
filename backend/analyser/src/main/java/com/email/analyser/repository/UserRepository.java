package com.email.analyser.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email.analyser.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
