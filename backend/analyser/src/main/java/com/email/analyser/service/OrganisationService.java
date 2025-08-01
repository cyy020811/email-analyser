package com.email.analyser.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.email.analyser.model.Domain;
import com.email.analyser.model.Organisation;
import com.email.analyser.repository.OrganisationRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrganisationService {
    private final OrganisationRepository organisationRepository;
    private final DomainService domainService;
    private final EmailHunterService emailHunterService;

    private Organisation getOrganisationByName(String name) {
        return organisationRepository.findOneByName(name);
    }

    private Organisation createOrganisation(String name, String logoUrl, String domainString) {
        Organisation organisation = Organisation.builder()
                .name(name)
                .logoUrl(logoUrl)
                .build();
        Domain domain = domainService.createDomain(domainString, organisation);
        organisation.getDomains().add(domain);
        organisationRepository.save(organisation);
        return organisation;
    }

    public Organisation getOrganisationByDomain(String domain) {
        Organisation organisation = organisationRepository.findOneByDomain(domain);
        if (organisation != null)
            return organisation;

        Map<String, String> result = emailHunterService.getOrganisationName(domain);

        String name = result.get("name");
        String logoUrl = result.get("logoUrl");

        if (name == null)
            return null;

        organisation = getOrganisationByName(name);
        if (organisation != null) {
            organisation.getDomains().add(domainService.createDomain(domain, organisation));
            organisationRepository.save(organisation);
            return organisation;
        }

        return createOrganisation(name, logoUrl, domain);
    }
}
