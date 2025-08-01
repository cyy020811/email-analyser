package com.email.analyser.model;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "emails", schema = "public")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "sender")
    private String sender;

    @Column(name = "snippet")
    private String snippet;

    @Column(name = "received_at")
    private Timestamp receivedAt;

    @Column(name = "thread_id")
    private String threadId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @Column(name = "created_at")
    private Timestamp createdAt;
}
