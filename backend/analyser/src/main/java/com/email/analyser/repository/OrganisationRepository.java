package com.email.analyser.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.email.analyser.model.Organisation;

public interface OrganisationRepository extends CrudRepository<Organisation, UUID> {
    Organisation findOneByName(String name);

    @Query(value = "SELECT o from Organisation o JOIN o.domains d WHERE d.domain = :domain")
    Organisation findOneByDomain(@Param("domain") String domain);
}
