package com.Veridia.CidadeVeridiaOficial.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Priority;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity (name = "Notificacao")
@Table (name = "notificacao")
public class Notificacao {
    @Id
    private UUID id;
    private NotificacaoTipoEvento tipoEvento;
    private NotificacaoTemplate template;
    private String title, mensagem;
    private JsonNode data;
    private Priority prioridade;
    private Usuario originUser;
    private Instant criado_em, mandado_em;
    private boolean cancelada;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public NotificacaoTipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(NotificacaoTipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public NotificacaoTemplate getTemplate() {
        return template;
    }

    public void setTemplate(NotificacaoTemplate template) {
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public Priority getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Priority prioridade) {
        this.prioridade = prioridade;
    }

    public Instant getMandado_em() {
        return mandado_em;
    }

    public void setMandado_em(Instant mandado_em) {
        this.mandado_em = mandado_em;
    }

    public Usuario getOriginUser() {
        return originUser;
    }

    public void setOriginUser(Usuario originUser) {
        this.originUser = originUser;
    }

    public Instant getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(Instant criado_em) {
        this.criado_em = criado_em;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }
}
