package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity (name = "Matricula")
@Table (name = "matriculas")
public class Matricula {
    @Id
    private UUID id;
    private Curso curso;
    private Usuario aluno;
    private MatriculaStatus status;
    private Instant matriculado_em;
    private enum MatriculaStatus{PENDENTE, APROVADO, FALHA};

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public Instant getMatriculado_em() {
        return matriculado_em;
    }

    public void setMatriculado_em(Instant matriculado_em) {
        this.matriculado_em = matriculado_em;
    }

    public MatriculaStatus getStatus() {
        return status;
    }

    public void setStatus(MatriculaStatus status) {
        this.status = status;
    }
}
