package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "notificacao") // nota: sua tabela é singular
public class Notificacao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // FK notificacao_tipo_evento(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id")
    private NotificacaoTipoEvento eventType;

    // FK notificacao_template(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private NotificacaoTemplate template;

    @Column(name = "title")
    private String title;

    @Column(name = "message", columnDefinition = "text", nullable = false)
    private String message;

    // jsonb -> mapeado como String; se quiser JsonNode/Map, usar hibernate-types
    @Column(name = "data", columnDefinition = "jsonb")
    private String data;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private Prioridade priority;

    // FK usuarios(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_user_id")
    private Usuario originUser;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "send_at")
    private Instant sendAt;

    @Column(name = "cancelled", nullable = false)
    private boolean cancelled = false;

    public enum Prioridade { URGENTE, MEDIA, BAIXA }

    public Notificacao() { }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public NotificacaoTipoEvento getEventType() { return eventType; }
    public void setEventType(NotificacaoTipoEvento eventType) { this.eventType = eventType; }

    public NotificacaoTemplate getTemplate() { return template; }
    public void setTemplate(NotificacaoTemplate template) { this.template = template; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public Prioridade getPriority() { return priority; }
    public void setPriority(Prioridade priority) { this.priority = priority; }

    public Usuario getOriginUser() { return originUser; }
    public void setOriginUser(Usuario originUser) { this.originUser = originUser; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getSendAt() { return sendAt; }
    public void setSendAt(Instant sendAt) { this.sendAt = sendAt; }

    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID();
        if (this.createdAt == null) this.createdAt = Instant.now();
        if (this.priority == null) this.priority = Prioridade.MEDIA;
    }
}
