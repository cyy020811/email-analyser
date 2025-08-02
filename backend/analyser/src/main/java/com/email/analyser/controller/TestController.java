package com.email.analyser.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.analyser.service.google.GmailService;
import com.google.api.services.gmail.model.Message;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final GmailService gmailService;

    @GetMapping
    public void getEmails(@RequestHeader("Authorization") String authoriazation) {

        String token = authoriazation.replace("Bearer ", "").trim();

        List<Message> messages = gmailService.getUserGmails(token, null);
        System.out.println(messages.get(0));
    }

}
