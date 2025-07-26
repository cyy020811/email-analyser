package com.email.analyser.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.analyser.service.google.GmailService;
import com.google.api.client.auth.oauth2.TokenResponse;

import lombok.AllArgsConstructor;

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

        TokenResponse tokenResponse = new TokenResponse()
                .setAccessToken(token);

        gmailService.getUserGmails(tokenResponse);
        System.out.println(tokenResponse);
    }

}
