package com.email.analyser.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email.analyser.model.Email;

public interface EmailRepository extends JpaRepository<Email, UUID> {
    List<Email> findByUserId(UUID userId);

    long countByUserId(UUID userId);
}
