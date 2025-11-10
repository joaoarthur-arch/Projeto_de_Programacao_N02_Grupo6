package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "mensagem_sistema")
public class MensagemSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(nullable = false, length = 50)
    private String tipo; // AVISO, MANUTENCAO, ATUALIZACAO, ALERTA

    @Column(length = 20)
    private String prioridade = "MEDIA";

    @Column(name = "data_inicio", nullable = false)
    private Instant dataInicio;

    @Column(name = "data_fim")
    private Instant dataFim;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "exibir_popup")
    private Boolean exibirPopup = false;

    @Column(name = "tipo_usuario", length = 50)
    private String tipoUsuario;

    @Column(name = "criado_por")
    private UUID criadoPor;

    @Column(name = "criado_em")
    private Instant criadoEm;

    @PrePersist
    public void prePersist() {
        if (this.criadoEm == null) {
            this.criadoEm = Instant.now();
        }
        if (this.dataInicio == null) {
            this.dataInicio = Instant.now();
        }
    }

    public MensagemSistema() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }

    public Instant getDataInicio() { return dataInicio; }
    public void setDataInicio(Instant dataInicio) { this.dataInicio = dataInicio; }

    public Instant getDataFim() { return dataFim; }
    public void setDataFim(Instant dataFim) { this.dataFim = dataFim; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public Boolean getExibirPopup() { return exibirPopup; }
    public void setExibirPopup(Boolean exibirPopup) { this.exibirPopup = exibirPopup; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public UUID getCriadoPor() { return criadoPor; }
    public void setCriadoPor(UUID criadoPor) { this.criadoPor = criadoPor; }

    public Instant getCriadoEm() { return criadoEm; }
    public void setCriadoEm(Instant criadoEm) { this.criadoEm = criadoEm; }
}