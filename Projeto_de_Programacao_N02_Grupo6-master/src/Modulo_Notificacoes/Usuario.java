import java.util.ArrayList;
import java.util.List;


public abstract class Usuario {
    protected String matricula;
    protected String nome;
    protected String email;
    protected String telefone;
    protected List< Notificacao> notificacoes;


    public Usuario(String matricula, String nome, String email, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.notificacoes = new ArrayList<>();
    }


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


    public void adicionarNotificacao(Notificacao notificacao) {
        this.notificacoes.add(notificacao);
    }


    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }
}
