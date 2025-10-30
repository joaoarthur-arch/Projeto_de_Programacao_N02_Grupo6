package com.Veridia.CidadeVeridiaOficial.controllers;

import com.Veridia.CidadeVeridiaOficial.dtos.AulaDTO;
import com.Veridia.CidadeVeridiaOficial.model.Aula;
import com.Veridia.CidadeVeridiaOficial.model.Curso;
import com.Veridia.CidadeVeridiaOficial.model.Usuario;
import com.Veridia.CidadeVeridiaOficial.repository.AulaRepository;
import com.Veridia.CidadeVeridiaOficial.repository.CursoRepository;
import com.Veridia.CidadeVeridiaOficial.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaRepository aulaRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public AulaController(AulaRepository aulaRepository,
                          CursoRepository cursoRepository,
                          UsuarioRepository usuarioRepository) {
        this.aulaRepository = aulaRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<AulaDTO> listAll() {
        return aulaRepository.findAll()
                .stream()
                .map(AulaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> getById(@PathVariable UUID id) {
        return aulaRepository.findById(id)
                .map(aula -> ResponseEntity.ok(AulaDTO.fromEntity(aula)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody AulaDTO dto) {
        Curso curso = null;
        Usuario instructor = null;

        if (dto.cursoId() != null) {
            Optional<Curso> c = cursoRepository.findById(dto.cursoId());
            if (c.isEmpty()) return ResponseEntity.badRequest().body("Curso não encontrado: " + dto.cursoId());
            curso = c.get();
        }

        if (dto.instructorId() != null) {
            Optional<Usuario> u = usuarioRepository.findById(dto.instructorId());
            if (u.isEmpty()) return ResponseEntity.badRequest().body("Instructor (usuario) não encontrado: " + dto.instructorId());
            instructor = u.get();
        }

        Aula aula = new Aula();
        aula.setCurso(curso);
        aula.setInstructor(instructor);
        aula.setTitle(dto.title());
        aula.setStartAt(dto.startAt());
        aula.setEndAt(dto.endAt());
        aula.setRoom(dto.room());

        Aula saved = aulaRepository.save(aula);
        URI location = URI.create("/api/aulas/" + saved.getId());
        return ResponseEntity.created(location).body(AulaDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Validated @RequestBody AulaDTO dto) {
        Optional<Aula> maybe = aulaRepository.findById(id);
        if (maybe.isEmpty()) return ResponseEntity.notFound().build();

        Aula aula = maybe.get();

        if (dto.cursoId() != null) {
            Optional<Curso> c = cursoRepository.findById(dto.cursoId());
            if (c.isEmpty()) return ResponseEntity.badRequest().body("Curso não encontrado: " + dto.cursoId());
            aula.setCurso(c.get());
        } else {
            aula.setCurso(null);
        }

        if (dto.instructorId() != null) {
            Optional<Usuario> u = usuarioRepository.findById(dto.instructorId());
            if (u.isEmpty()) return ResponseEntity.badRequest().body("Instructor (usuario) não encontrado: " + dto.instructorId());
            aula.setInstructor(u.get());
        } else {
            aula.setInstructor(null);
        }

        aula.setTitle(dto.title());
        aula.setStartAt(dto.startAt());
        aula.setEndAt(dto.endAt());
        aula.setRoom(dto.room());

        Aula updated = aulaRepository.save(aula);
        return ResponseEntity.ok(AulaDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Optional<Aula> maybe = aulaRepository.findById(id);
        if (maybe.isEmpty()) return ResponseEntity.notFound().build();
        aulaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
