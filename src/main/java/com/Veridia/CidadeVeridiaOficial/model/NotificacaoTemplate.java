package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "notificacao_template")
public class NotificacaoTemplate {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // no schema: column name = name (UNIQUE)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body", columnDefinition = "text", nullable = false)
    private String body;

    // channel is text[] with default ['EM_APP']
    @Column(name = "channel", columnDefinition = "text[]")
    private String[] channel;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public NotificacaoTemplate() { }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String[] getChannel() { return channel; }
    public void setChannel(String[] channel) { this.channel = channel; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID();
        if (this.createdAt == null) this.createdAt = Instant.now();
        if (this.channel == null) this.channel = new String[] { "EM_APP" };
    }
}
