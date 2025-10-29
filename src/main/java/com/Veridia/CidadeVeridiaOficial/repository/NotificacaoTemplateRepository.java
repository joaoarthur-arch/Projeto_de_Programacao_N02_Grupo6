package com.Veridia.CidadeVeridiaOficial.repository;

import com.Veridia.CidadeVeridiaOficial.model.NotificacaoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface NotificacaoTemplateRepository extends JpaRepository<NotificacaoTemplate, UUID> {
}
