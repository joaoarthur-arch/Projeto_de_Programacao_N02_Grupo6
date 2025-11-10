package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "preferencias_notificacao")
public class PreferenciasNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "usuario_id", nullable = false, unique = true)
    private UUID usuarioId;

    @Column(name = "email_ativo")
    private Boolean emailAtivo = true;

    @Column(name = "notif_nova_aula")
    private Boolean notifNovaAula = true;

    @Column(name = "notif_cancelamento")
    private Boolean notifCancelamento = true;

    @Column(name = "notif_pagamento")
    private Boolean notifPagamento = true;

    @Column(name = "notif_avaliacao")
    private Boolean notifAvaliacao = true;

    public PreferenciasNotificacao() {}

    public PreferenciasNotificacao(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    public Boolean getEmailAtivo() { return emailAtivo; }
    public void setEmailAtivo(Boolean emailAtivo) { this.emailAtivo = emailAtivo; }

    public Boolean getNotifNovaAula() { return notifNovaAula; }
    public void setNotifNovaAula(Boolean notifNovaAula) { this.notifNovaAula = notifNovaAula; }

    public Boolean getNotifCancelamento() { return notifCancelamento; }
    public void setNotifCancelamento(Boolean notifCancelamento) { this.notifCancelamento = notifCancelamento; }

    public Boolean getNotifPagamento() { return notifPagamento; }
    public void setNotifPagamento(Boolean notifPagamento) { this.notifPagamento = notifPagamento; }

    public Boolean getNotifAvaliacao() { return notifAvaliacao; }
    public void setNotifAvaliacao(Boolean notifAvaliacao) { this.notifAvaliacao = notifAvaliacao; }
}