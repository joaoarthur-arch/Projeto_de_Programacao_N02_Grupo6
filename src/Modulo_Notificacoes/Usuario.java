package Modulo_Notificacoes;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um usuário genérico do sistema, com informações comuns a alunos e instrutores.
 * Contém dados básicos e histórico de notificações.
 */
public abstract class Usuario {
    protected String matricula;         // Identificação única (matrícula) do usuário
    protected String nome;              // Nome completo do usuário
    protected String email;             // E-mail de contato
    protected String telefone;          // Telefone de contato do usuário
    protected List<Notificacao> notificacoes;  // Histórico de notificações recebidas pelo usuário

    /**
     * Construtor da classe Usuario.
     *
     * @param matricula A matrícula (ID) do usuário (chave primária).
     * @param nome      O nome completo do usuário.
     * @param email     O e-mail do usuário.
     * @param telefone  O telefone de contato do usuário.
     */
    public Usuario(String matricula, String nome, String email, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.notificacoes = new ArrayList<>();
    }

    // Getters e setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Adiciona uma notificação ao histórico do usuário.
     *
     * @param notificacao A notificação a ser adicionada.
     */
    public void adicionarNotificacao(Notificacao notificacao) {
        this.notificacoes.add(notificacao);
    }

    /**
     * Retorna o histórico de notificações do usuário.
     *
     * @return Lista de notificações recebidas pelo usuário.
     */
    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }
}
