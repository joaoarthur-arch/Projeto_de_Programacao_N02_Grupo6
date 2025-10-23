public class NotificacaoService {

    public void enviarNotificacao(Usuario usuario, Notificacao notificacao) {
        usuario.adicionarNotificacao(notificacao);
        // Lógica adicional de envio (ex: popup na interface) pode ser adicionada aqui.
    }
}
