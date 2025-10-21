package Modulo_Notificacoes;

import java.time.LocalDateTime;


public class Notificacao {
    private String mensagem;
    private LocalDateTime dataHora;
    private TipoNotificacao tipo;
    private Usuario destinatario;


    public Notificacao(String mensagem, TipoNotificacao tipo, Usuario destinatario) {
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.destinatario = destinatario;
        this.dataHora = LocalDateTime.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public TipoNotificacao getTipo() {
        return tipo;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    @Override
    public String toString() {
        return "[" + dataHora + "] (" + tipo + ") " + mensagem;
    }
}
