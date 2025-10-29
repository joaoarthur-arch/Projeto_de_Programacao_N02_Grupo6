package com.Veridia.CidadeVeridiaOficial.repository;

import com.Veridia.CidadeVeridiaOficial.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
}
