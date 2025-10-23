import java.time.LocalDate;

public class Avaliacao {
    private double nota;           
    private LocalDate data;        
    private String descricao;      
    private Aluno aluno;           

    public Avaliacao(double nota, LocalDate data, String descricao, Aluno aluno) {
        this.nota = nota;
        this.data = data;
        this.descricao = descricao;
        this.aluno = aluno;
    }

    public double getNota() {
        return nota;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Aluno getAluno() {
        return aluno;
    }
}
