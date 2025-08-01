package com.email.analyser.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.email.analyser.model.Organisation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDto {
    private UUID id;
    private String messageId;
    private String subject;
    private String sender;
    private String snippet;
    private Timestamp receivedAt;
    private String threadId;
    private Organisation organisation;
    private Timestamp createdAt;
}
