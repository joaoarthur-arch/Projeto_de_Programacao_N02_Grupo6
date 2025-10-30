package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "notificacoes_entregues")
public class NotificacaoEntregaLog {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // FK notification_recipient_id -> notificacoes_para_destinatario.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_recipient_id")
    private NotificacaoDestinatario notificationRecipient;

    @Column(name = "attempt_at", nullable = false)
    private Instant attemptAt;

    // status CHECK ('SUCCESS','FAILED')
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "error")
    private String error;

    public NotificacaoEntregaLog() { }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public NotificacaoDestinatario getNotificationRecipient() { return notificationRecipient; }
    public void setNotificationRecipient(NotificacaoDestinatario notificationRecipient) { this.notificationRecipient = notificationRecipient; }

    public Instant getAttemptAt() { return attemptAt; }
    public void setAttemptAt(Instant attemptAt) { this.attemptAt = attemptAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID();
        if (this.attemptAt == null) this.attemptAt = Instant.now();
    }
}
