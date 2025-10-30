package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "aulas")
public class Aula {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // FK para cursos(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // FK para usuarios(id) -> coluna instructor_id no schema
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Usuario instructor;

    @Column(name = "title")
    private String title;

    @Column(name = "start_at", nullable = false)
    private String startAt;

    @Column(name = "end_at")
    private String endAt;

    @Column(name = "room")
    private String room;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public Aula() { }

    // getters / setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public Usuario getInstructor() { return instructor; }
    public void setInstructor(Usuario instructor) { this.instructor = instructor; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getStartAt() { return startAt; }
    public void setStartAt(String startAt) { this.startAt = startAt; }

    public String getEndAt() { return endAt; }
    public void setEndAt(String endAt) { this.endAt = endAt; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID();
        if (this.createdAt == null) this.createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
