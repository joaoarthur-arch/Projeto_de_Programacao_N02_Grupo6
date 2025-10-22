import java.time.LocalDate;

/**
 * Representa um pagamento (mensalidade, taxa, etc) no sistema.
 * Contém valor, datas de vencimento e pagamento, e status.
 */
public class Pagamento {
    private double valor;              // Valor do pagamento
    private LocalDate dataVencimento;  // Data de vencimento
    private LocalDate dataPagamento;   // Data em que o pagamento foi efetuado (nulo se ainda não pago)
    private boolean pago;              // Indica se o pagamento já foi efetuado

    public Pagamento(double valor, LocalDate dataVencimento) {
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.pago = false;
        this.dataPagamento = null;
    }

    
    
    public void registrarPagamento() {
        this.dataPagamento = LocalDate.now();
        this.pago = true;
    }

  
    public boolean estaAtrasado() {
        return !pago && LocalDate.now().isAfter(dataVencimento);
    }

    // Getters
    public double getValor() {
        return valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public boolean isPago() {
        return pago;
    }
}
