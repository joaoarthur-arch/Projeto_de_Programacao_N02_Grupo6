package Modulo_Notificacoes;

import java.util.ArrayList;
import java.util.List;


public class Instrutor extends Usuario {
    private List<Aula> aulasMinistradas;  // Aulas que o instrutor ministra


    public Instrutor(String matricula, String nome, String email, String telefone) {
        super(matricula, nome, email, telefone);
        this.aulasMinistradas = new ArrayList<>();
    }


    public void adicionarAula(Aula aula) {
        if (aula != null && !aulasMinistradas.contains(aula)) {
            aulasMinistradas.add(aula);
        }
    }


    public List<Aula> getAulasMinistradas() {
        return aulasMinistradas;
    }
}
