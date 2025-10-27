import java.util.ArrayList;
import java.util.List;

public class SistemaAcademico {
private List<Aluno> alunos;
private List<Instrutor> instrutores;
private List<Aula> aulas;
private NotificacaoService servicoNotificacao;

public SistemaAcademico() {
this.alunos = new ArrayList<>();
this.instrutores = new ArrayList<>();
this.aulas = new ArrayList<>();
this.servicoNotificacao = new NotificacaoService();

public void cadastrarAluno(Aluno aluno) {
if (aluno != null && buscarAluno(aluno.getMatricula()) == null) {
alunos.add(aluno);
}
}
/**
