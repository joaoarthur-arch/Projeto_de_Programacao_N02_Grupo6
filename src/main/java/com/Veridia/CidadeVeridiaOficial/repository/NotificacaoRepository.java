package com.Veridia.CidadeVeridiaOficial.repository;

import com.Veridia.CidadeVeridiaOficial.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {

    List<Notificacao> findByUsuarioIdOrderByDataEnvioDesc(UUID usuarioId);

    List<Notificacao> findByUsuarioIdAndLidoFalseOrderByDataEnvioDesc(UUID usuarioId);

    Long countByUsuarioIdAndLidoFalse(UUID usuarioId);

    List<Notificacao> findByTipoOrderByDataEnvioDesc(String tipo);
}