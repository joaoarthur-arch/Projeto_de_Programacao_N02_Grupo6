package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.nio.channels.Channel;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity (name = "NotificacaoTemplate")
@Table (name = "notificacao_template")
public class NotificacaoTemplate {
    @Id
    private UUID id;
    private String nome, conteudo, corpo;
    private List<Channel> channel;
    private Instant criado_em;
    private enum Channel{EM_APP, EMAIL};

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public List<Channel> getChannel() {
        return channel;
    }

    public void setChannel(List<Channel> channel) {
        this.channel = channel;
    }

    public Instant getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(Instant criado_em) {
        this.criado_em = criado_em;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
}
