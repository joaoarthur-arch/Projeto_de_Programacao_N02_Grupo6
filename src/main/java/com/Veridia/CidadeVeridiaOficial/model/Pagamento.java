package com.Veridia.CidadeVeridiaOficial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity (name = "Pagamento")
@Table (name = "pagamentos")
public class Pagamento {
    @Id
    private UUID id;
    private Matricula matricula;
    private BigDecimal quantia;
    private PagamentoStatus status;
    private Instant pago_em;
    private enum PagamentoStatus{PENDENTE, APROVADO, FALHA};

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getPago_em() {
        return pago_em;
    }

    public void setPago_em(Instant pago_em) {
        this.pago_em = pago_em;
    }

    public BigDecimal getQuantia() {
        return quantia;
    }

    public void setQuantia(BigDecimal quantia) {
        this.quantia = quantia;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public PagamentoStatus getStatus() {
        return status;
    }

    public void setStatus(PagamentoStatus status) {
        this.status = status;
    }
}
