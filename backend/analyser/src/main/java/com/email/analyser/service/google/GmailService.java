package com.email.analyser.service.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GmailService {
    private final GmailClientFactory gmailClientFactory;

    public List<Message> getUserGmails(String token) {
        try {
            TokenResponse tokenResponse = new TokenResponse()
                    .setAccessToken(token);
            Gmail gmail = gmailClientFactory.createClient(tokenResponse);
            ListMessagesResponse listMessagesResponse = gmail.users().messages().list("me").execute();
            List<Message> messages = listMessagesResponse.getMessages().stream().map(message -> {
                try {
                    return gmail.users().messages().get("me", message.getId())
                            .setFields("id,threadId,snippet,internalDate,payload.headers").execute();
                } catch (IOException e) {
                    System.err.println("IOException during API call: " + e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }).toList();
            return messages;
        } catch (IOException e) {
            System.err.println("IOException during API call: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (GeneralSecurityException e) {
            System.err.println("GeneralSecurityException during API call: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
