package com.email.analyser.service.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GmailService {
    private final GmailClientFactory gmailClientFactory;

    public ListMessagesResponse getUserGmails(TokenResponse tokenResponse) {
        try {
            Gmail gmail = gmailClientFactory.createClient(tokenResponse);
            return gmail.users().messages().list("me").execute();
        } catch (IOException e) {
            // TODO: handle exception
            System.err.println("IOException during API call: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (GeneralSecurityException e) {
            // TODO: handle exception
            System.out.println("security exeption");
            return null;
        }
    }
}
