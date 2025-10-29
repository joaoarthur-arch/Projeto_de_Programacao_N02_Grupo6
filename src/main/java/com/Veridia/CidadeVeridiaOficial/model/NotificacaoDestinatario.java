package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.nio.channels.Channel;
import java.time.Instant;
import java.util.UUID;

@Entity (name = "NotificacaoDestinatario")
@Table (name = "notificacoes_para_destinatario")
public class NotificacaoDestinatario {
    @Id
    private UUID id;
    private Notificacao notificacao;
    private Usuario destinatario;
    private Channel channel;
    private boolean encaminhada, lida;
    private Instant encaminhada_em, lida_em, criada_em;
    private int tentativas;

    public Notificacao getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public boolean isEncaminhada() {
        return encaminhada;
    }

    public void setEncaminhada(boolean encaminhada) {
        this.encaminhada = encaminhada;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Instant getEncaminhada_em() {
        return encaminhada_em;
    }

    public void setEncaminhada_em(Instant encaminhada_em) {
        this.encaminhada_em = encaminhada_em;
    }

    public Instant getLida_em() {
        return lida_em;
    }

    public void setLida_em(Instant lida_em) {
        this.lida_em = lida_em;
    }

    public Instant getCriada_em() {
        return criada_em;
    }

    public void setCriada_em(Instant criada_em) {
        this.criada_em = criada_em;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }
}
