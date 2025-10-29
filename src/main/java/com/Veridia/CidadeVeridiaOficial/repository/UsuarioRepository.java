// FAZ O CONTATO  COM O BANCO DE DADOS
package com.Veridia.CidadeVeridiaOficial.repository;
import com.Veridia.CidadeVeridiaOficial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
