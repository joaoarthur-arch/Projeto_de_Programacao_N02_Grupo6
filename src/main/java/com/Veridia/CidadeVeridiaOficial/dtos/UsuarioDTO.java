package com.Veridia.CidadeVeridiaOficial.dtos;

import com.Veridia.CidadeVeridiaOficial.model.Usuario;

import java.time.Instant;

public record UsuarioDTO(String username, String fullName, String email, Usuario.Role role, Instant createdAt) {
}
