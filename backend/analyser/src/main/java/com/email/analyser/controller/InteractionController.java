package com.email.analyser.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.analyser.dto.InteractionDto;
import com.email.analyser.service.InteractionService;
import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@AllArgsConstructor
@RequestMapping("/interactions")
public class InteractionController {

    private final InteractionService interactionService;

    @GetMapping("/{userId}")
    public Iterable<InteractionDto> getInteractions(@PathVariable UUID userId,
            @RequestHeader("Authorization") String authoriazation) {

        String token = authoriazation.replace("Bearer ", "").trim();
        return interactionService.getUserInteractions(userId, token);
    }
}
