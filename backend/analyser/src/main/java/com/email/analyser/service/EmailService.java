package com.email.analyser.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.email.analyser.dto.EmailDto;
import com.email.analyser.mapper.EmailMapper;
import com.email.analyser.model.Email;
import com.email.analyser.model.Organisation;
import com.email.analyser.model.User;
import com.email.analyser.repository.EmailRepository;
import com.email.analyser.service.google.GmailService;
import com.google.api.services.gmail.model.Message;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailService {
    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;
    private final GmailService gmailService;
    private final OrganisationService organisationService;

    public Iterable<EmailDto> getUserEmails(UUID userId) {
        return emailRepository.findByUserId(userId)
                .stream()
                .map(emailMapper::toDto)
                .toList();
    }

    public Iterable<EmailDto> getUserGmailMessages(String token, User user, Map<String, String> queryParams) {
        List<Message> messages = gmailService.getUserGmails(token, queryParams);
        List<Email> emails = new ArrayList<Email>();
        HashMap<String, Organisation> domainOrganisationMap = new HashMap<String, Organisation>();

        for (Message message : messages) {
            Email email = emailMapper.toEntity(message);
            String sender = email.getSender();
            String domain = sender.substring(sender.indexOf("@") + 1);
            if (domainOrganisationMap.containsKey(domain)) {
                email.setOrganisation(domainOrganisationMap.get(domain));
            } else {
                Organisation organisation = organisationService.getOrganisationByDomain(domain);
                if (organisation == null)
                    continue;

                domainOrganisationMap.put(domain, organisation);
                email.setOrganisation(organisation);
            }

            email.setUser(user);
            emails.add(email);
        }
        emailRepository.saveAll(emails);

        return emails.stream().map(emailMapper::toDto).toList();
    }

    public long getEmailCountByUserId(UUID userId) {
        return emailRepository.countByUserId(userId);
    }
}
