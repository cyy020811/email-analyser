package com.email.analyser.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.email.analyser.dto.InteractionDto;
import com.email.analyser.mapper.InteractionMapper;
import com.email.analyser.repository.InteractionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InteractionService {
    private final InteractionRepository interactionRepository;
    private final InteractionMapper interactionMapper;

    public Iterable<InteractionDto> getUserInteractions(UUID userId) {
        return interactionRepository.findByUserId(userId)
                .stream()
                .map(interactionMapper::tDto)
                .toList();
    }

    public void deleteInteraction(UUID id) {
        var interaction = interactionRepository.findById(id).orElseThrow();
        interactionRepository.delete(interaction);
    }
}
