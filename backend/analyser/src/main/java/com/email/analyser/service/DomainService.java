package com.email.analyser.service;

import org.springframework.stereotype.Service;

import com.email.analyser.model.Domain;
import com.email.analyser.model.Organisation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DomainService {

    public Domain createDomain(String domainString, Organisation organisation) {
        Domain domain = Domain.builder().domain(domainString).organisation(organisation).build();
        return domain;
    }
}
