package com.email.analyser.service;

import java.util.UUID;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.email.analyser.dto.InteractionDto;
import com.email.analyser.mapper.EmailMapper;
import com.email.analyser.mapper.InteractionMapper;
import com.email.analyser.model.Email;
import com.email.analyser.model.Interaction;
import com.email.analyser.model.Organisation;
import com.email.analyser.model.User;
import com.email.analyser.repository.InteractionRepository;
import com.email.analyser.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InteractionService {
    private final InteractionRepository interactionRepository;
    private final InteractionMapper interactionMapper;
    private final EmailMapper emailMapper;
    private final EmailService emailService;
    private final UserRepository userRepository;

    public Iterable<InteractionDto> getUserInteractions(UUID userId, String token) {
        List<Interaction> interactions = interactionRepository.findByUserId(userId);

        if (interactions.size() == 0) {
            interactions = initInteractions(userId, token);
        }
        return interactions.stream()
                .map(interactionMapper::toDto)
                .toList();

    }

    public void deleteInteraction(UUID id) {
        var interaction = interactionRepository.findById(id).orElseThrow();
        interactionRepository.delete(interaction);
    }

    // Use latest email timestamp
    public InteractionDto updateInteraction(UUID id) {
        return null;
    }

    private List<Interaction> initInteractions(UUID userId, String token) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Email> emails = emailMapper.toEmailList(emailService.getUserGmailMessages(token, user, null));
        return generateInteractions(emails, user);
    }

    private List<Interaction> generateInteractions(List<Email> emails, User user) {
        Map<Organisation, Interaction> orgInteractionMap = new HashMap<>();

        emails.forEach(email -> {
            Organisation organisation = email.getOrganisation();
            if (!orgInteractionMap.containsKey(organisation)) {
                orgInteractionMap.put(organisation,
                        Interaction.builder()
                                .lastContacted(email.getReceivedAt())
                                .organisation(organisation)
                                .totalEmails(1)
                                .user(user)
                                .build());
            } else {
                Interaction interaction = orgInteractionMap.get(organisation);
                Timestamp lastContacted = email.getReceivedAt().after(interaction.getLastContacted())
                        ? email.getReceivedAt()
                        : interaction.getLastContacted();
                interaction.setTotalEmails(interaction.getTotalEmails() + 1);
                interaction.setLastContacted(lastContacted);
            }
        });

        interactionRepository.saveAll(orgInteractionMap.values());
        return new ArrayList<>(orgInteractionMap.values());
    }
}