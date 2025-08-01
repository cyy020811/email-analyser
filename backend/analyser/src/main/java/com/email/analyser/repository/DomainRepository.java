package com.email.analyser.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.email.analyser.model.Domain;

public interface DomainRepository extends CrudRepository<Domain, UUID> {
    Domain findOneByDomain(String domain);
}
