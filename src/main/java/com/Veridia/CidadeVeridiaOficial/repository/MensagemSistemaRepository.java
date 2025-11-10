package com.Veridia.CidadeVeridiaOficial.repository;

import com.Veridia.CidadeVeridiaOficial.model.MensagemSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface MensagemSistemaRepository extends JpaRepository<MensagemSistema, UUID> {

    List<MensagemSistema> findByAtivoTrueOrderByDataInicioDesc();

    @Query("SELECT m FROM MensagemSistema m WHERE m.ativo = true AND m.exibirPopup = true " +
            "AND m.dataInicio <= :agora AND (m.dataFim IS NULL OR m.dataFim >= :agora)")
    List<MensagemSistema> findMensagensPopup(Instant agora);

    @Query("SELECT m FROM MensagemSistema m WHERE m.ativo = true " +
            "AND m.dataInicio <= :agora AND (m.dataFim IS NULL OR m.dataFim >= :agora)")
    List<MensagemSistema> findMensagensVigentes(Instant agora);
}