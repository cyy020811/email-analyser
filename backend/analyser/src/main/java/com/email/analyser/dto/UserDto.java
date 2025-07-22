package com.email.analyser.dto;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String email;
    private String role;
    private Timestamp lastSignInAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}