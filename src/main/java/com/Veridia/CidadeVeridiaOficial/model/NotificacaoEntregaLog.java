package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity (name = "NotificacaoEntregaLog")
@Table (name = "notificacoes_entregues")
public class NotificacaoEntregaLog {
    @Id
    private UUID id;
    private NotificacaoDestinatario destinatario;
    private Instant tentativa_de;
    private DeliveryStatus status;
    private String erro;
    private enum DeliveryStatus{SUCESSO, FALHA};

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public NotificacaoDestinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(NotificacaoDestinatario destinatario) {
        this.destinatario = destinatario;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public Instant getTentativa_de() {
        return tentativa_de;
    }

    public void setTentativa_de(Instant tentativa_de) {
        this.tentativa_de = tentativa_de;
    }
}
