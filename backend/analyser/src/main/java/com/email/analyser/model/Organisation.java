package com.email.analyser.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@Table(name = "organisations", schema = "public")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "logo_url")
    private String logoUrl;

    @EqualsAndHashCode.Include
    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "organisation", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @Builder.Default
    @ToString.Exclude
    private Set<Domain> domains = new HashSet<Domain>();

}
