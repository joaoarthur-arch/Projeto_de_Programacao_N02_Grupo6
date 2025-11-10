package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(nullable = false, length = 50)
    private String tipo; // NOVA_AULA, CANCELAMENTO, PAGAMENTO, AVALIACAO, MENSAGEM

    @Column(length = 20)
    private String prioridade = "MEDIA"; // URGENTE, MEDIA, BAIXA

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(nullable = false)
    private Boolean lido = false;

    @Column(name = "data_envio")
    private Instant dataEnvio;

    @Column(name = "data_leitura")
    private Instant dataLeitura;

    @PrePersist
    public void prePersist() {
        if (this.dataEnvio == null) {
            this.dataEnvio = Instant.now();
        }
    }

    public Notificacao() {}

    public Notificacao(String titulo, String mensagem, String tipo, UUID usuarioId) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.usuarioId = usuarioId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    public Boolean getLido() { return lido; }
    public void setLido(Boolean lido) { this.lido = lido; }

    public Instant getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(Instant dataEnvio) { this.dataEnvio = dataEnvio; }

    public Instant getDataLeitura() { return dataLeitura; }
    public void setDataLeitura(Instant dataLeitura) { this.dataLeitura = dataLeitura; }
}