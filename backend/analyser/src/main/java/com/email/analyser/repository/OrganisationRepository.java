package com.email.analyser.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.email.analyser.model.Organisation;

public interface OrganisationRepository extends CrudRepository<Organisation, UUID> {
    Organisation findOneByName(String name);
}
