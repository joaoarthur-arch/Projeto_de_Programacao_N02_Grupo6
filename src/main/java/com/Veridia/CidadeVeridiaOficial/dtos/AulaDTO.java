package com.Veridia.CidadeVeridiaOficial.dtos;

import com.Veridia.CidadeVeridiaOficial.model.Aula;
import java.time.Instant;
import java.util.UUID;

public record AulaDTO(
        UUID cursoId,
        UUID instructorId,
        String title,
        String startAt,
        String endAt,
        String room,
        Instant createdAt
) {
    public static AulaDTO fromEntity(Aula aula) {
        if (aula == null) return null;
        UUID cursoId = aula.getCurso() != null ? aula.getCurso().getId() : null;
        UUID instructorId = aula.getInstructor() != null ? aula.getInstructor().getId() : null;
        return new AulaDTO(
                cursoId,
                instructorId,
                aula.getTitle(),
                aula.getStartAt(),
                aula.getEndAt(),
                aula.getRoom(),
                aula.getCreatedAt()
        );
    }
}
