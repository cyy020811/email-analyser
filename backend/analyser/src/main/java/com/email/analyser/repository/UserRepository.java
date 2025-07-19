package com.email.analyser.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.email.analyser.model.User;

public interface UserRepository extends CrudRepository<User, UUID> {

}
