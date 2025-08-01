package com.email.analyser.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.email.analyser.model.Interaction;

public interface InteractionRepository extends CrudRepository<Interaction, UUID> {
    List<Interaction> findByUserId(UUID userId);
}
