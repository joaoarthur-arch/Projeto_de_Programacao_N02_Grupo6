import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma aula (sessão de ensino) no sistema.
 * Contém informações como nome, instrutor, alunos, horário e status.
 */
public class Aula {
    private String nome;                // Nome ou descrição da aula
    private Instrutor instrutor;        // Instrutor que ministra a aula
    private List<Aluno> alunos;         // Lista de alunos matriculados na aula
    private LocalDateTime dataHora;     // Data e hora marcada para a aula
    private boolean cancelada;          // Indica se a aula foi cancelada


    public Aula(String nome, Instrutor instrutor, LocalDateTime dataHora) {
        this.nome = nome;
        this.instrutor = instrutor;
        this.dataHora = dataHora;
        this.alunos = new ArrayList<>();
        this.cancelada = false;
        if (instrutor != null) {
            instrutor.adicionarAula(this);
        }
    }

  
    public void adicionarAluno(Aluno aluno) {
        if (aluno != null && !alunos.contains(aluno)) {
            alunos.add(aluno);
        }
    }

  
    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    
    public void cancelarAula(NotificacaoService servicoNotificacao) {
        this.cancelada = true;
        String msg = "Aula " + nome + " no horário " + dataHora + " foi cancelada.";
        for (Aluno aluno : alunos) {
            Notificacao notificacao = new Notificacao(msg, TipoNotificacao.AMARELO, aluno);
            servicoNotificacao.enviarNotificacao(aluno, notificacao);
        }
    }

   
    public void confirmarAula(NotificacaoService servicoNotificacao) {
        String msg = "Aula " + nome + " confirmada para " + dataHora + ".";
        for (Aluno aluno : alunos) {
            Notificacao notificacao = new Notificacao(msg, TipoNotificacao.BRANCO, aluno);
            servicoNotificacao.enviarNotificacao(aluno, notificacao);
        }
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
