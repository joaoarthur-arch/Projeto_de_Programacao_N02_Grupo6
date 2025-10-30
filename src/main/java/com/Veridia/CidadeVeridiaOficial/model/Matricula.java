package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // FK cursos(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // FK usuarios(id) -> coluna aluno_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Usuario aluno;

    // status tem default 'Pendente' e CHECK (Pendente,Aprovado,Rejeitado)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MatriculaStatus status;

    // corresponde à coluna enrolled_at
    @Column(name = "enrolled_at", nullable = false)
    private Instant enrolledAt;

    // nomes do DB: 'Pendente', 'Aprovado', 'Rejeitado'
    public enum MatriculaStatus { Pendente, Aprovado, Rejeitado }

    public Matricula() { }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public Usuario getAluno() { return aluno; }
    public void setAluno(Usuario aluno) { this.aluno = aluno; }

    public MatriculaStatus getStatus() { return status; }
    public void setStatus(MatriculaStatus status) { this.status = status; }

    public Instant getEnrolledAt() { return enrolledAt; }
    public void setEnrolledAt(Instant enrolledAt) { this.enrolledAt = enrolledAt; }

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID();
        if (this.enrolledAt == null) this.enrolledAt = Instant.now();
        if (this.status == null) this.status = MatriculaStatus.Pendente;
    }
}
