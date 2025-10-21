package Modulo_Notificacoes;
import java.util.ArrayList;
import java.util.List;

//  Representa um aluno do sistema.
// Herda de Usuario e inclui informações acadêmicas e financeiras

public class Aluno extends Usuario {
    private List<Pagamento> pagamentos;      // Histórico de pagamentos do aluno
    private List<Avaliacao> avaliacoes;      // Histórico de avaliações do aluno
    private List<Aula> aulasMatriculadas;    // Aulas em que o aluno está matriculado


    public Aluno(String matricula, String nome, String email, String telefone) {
        super(matricula, nome, email, telefone);
        this.pagamentos = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
        this.aulasMatriculadas = new ArrayList<>();
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void adicionarPagamento(Pagamento pagamento) {
        this.pagamentos.add(pagamento);
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    /**
     * Matricula o aluno em uma aula.
     * Adiciona a aula à lista de aulas matriculadas do aluno.
     *
     * @param aula A aula em que o aluno será matriculado.
     */
    public void matricularEmAula(Aula aula) {
        if (aula != null && !aulasMatriculadas.contains(aula)) {
            aulasMatriculadas.add(aula);
            aula.adicionarAluno(this);
        }
    }

    /**
     * Retorna a lista de aulas nas quais o aluno está matriculado.
     *
     * @return Lista de aulas.
     */
    public List<Aula> getAulasMatriculadas() {
        return aulasMatriculadas;
    }
}

