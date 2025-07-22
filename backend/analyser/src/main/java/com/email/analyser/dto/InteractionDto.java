package com.email.analyser.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.email.analyser.model.Organisation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InteractionDto {
    private UUID id;
    private Integer totalEmails;
    private Timestamp lastContacted;
    private String summary;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Organisation organisation;
}
