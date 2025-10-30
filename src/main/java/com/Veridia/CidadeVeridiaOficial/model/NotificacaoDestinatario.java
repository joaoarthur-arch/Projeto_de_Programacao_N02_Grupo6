package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "notificacoes_para_destinatario")
public class NotificacaoDestinatario {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // FK notification_id -> notificacao.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    private Notificacao notification;

    // FK recipient_id -> usuarios.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    private Usuario recipient;

    // channel CHECK ('EM_APP','EMAIL') -> mapeamos como enum
    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false)
    private DeliveryChannel channel;

    @Column(name = "delivered", nullable = false)
    private boolean delivered = false;

    @Column(name = "delivered_at")
    private Instant deliveredAt;

    @Column(name = "read", nullable = false)
    private boolean read = false;

    @Column(name = "read_at")
    private Instant readAt;

    @Column(name = "attempts", nullable = false)
    private Integer attempts = 0;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public enum DeliveryChannel { EM_APP, EMAIL }

    public NotificacaoDestinatario() { }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Notificacao getNotification() { return notification; }
    public void setNotification(Notificacao notification) { this.notification = notification; }

    public Usuario getRecipient() { return recipient; }
    public void setRecipient(Usuario recipient) { this.recipient = recipient; }

    public DeliveryChannel getChannel() { return channel; }
    public void setChannel(DeliveryChannel channel) { this.channel = channel; }

    public boolean isDelivered() { return delivered; }
    public void setDelivered(boolean delivered) { this.delivered = delivered; }

    public Instant getDeliveredAt() { return deliveredAt; }
    public void setDeliveredAt(Instant deliveredAt) { this.deliveredAt = deliveredAt; }

    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }

    public Instant getReadAt() { return readAt; }
    public void setReadAt(Instant readAt) { this.readAt = readAt; }

    public Integer getAttempts() { return attempts; }
    public void setAttempts(Integer attempts) { this.attempts = attempts; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID();
        if (this.createdAt == null) this.createdAt = Instant.now();
        if (this.attempts == null) this.attempts = 0;
        if (this.channel == null) this.channel = DeliveryChannel.EM_APP;
    }
}
