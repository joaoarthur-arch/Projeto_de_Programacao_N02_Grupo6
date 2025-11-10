package com.Veridia.CidadeVeridiaOficial.Service;

import com.Veridia.CidadeVeridiaOficial.model.Notificacao;
import com.Veridia.CidadeVeridiaOficial.model.MensagemSistema;
import com.Veridia.CidadeVeridiaOficial.model.PreferenciasNotificacao;
import com.Veridia.CidadeVeridiaOficial.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private MensagemSistemaRepository mensagemRepository;

    @Autowired
    private PreferenciasNotificacaoRepository preferenciasRepository;

    @Transactional
    public Notificacao enviarNotificacao(String titulo, String mensagem, String tipo,
                                         String prioridade, UUID usuarioId) {

        if (!deveEnviar(usuarioId, tipo)) {
            System.out.println("⚠️ Usuário desativou notificações de tipo: " + tipo);
            return null;
        }

        Notificacao notif = new Notificacao();
        notif.setTitulo(titulo);
        notif.setMensagem(mensagem);
        notif.setTipo(tipo);
        notif.setPrioridade(prioridade);
        notif.setUsuarioId(usuarioId);
        notif.setLido(false);

        notif = notificacaoRepository.save(notif);

        simularEnvioEmail(notif);

        return notif;
    }

    @Transactional
    public List<Notificacao> enviarParaVarios(String titulo, String mensagem,
                                              String tipo, String prioridade,
                                              List<UUID> usuariosIds) {
        List<Notificacao> notificacoes = new ArrayList<>();

        for (UUID usuarioId : usuariosIds) {
            Notificacao n = enviarNotificacao(titulo, mensagem, tipo, prioridade, usuarioId);
            if (n != null) {
                notificacoes.add(n);
            }
        }

        return notificacoes;
    }


    public List<Notificacao> buscarPorUsuario(UUID usuarioId) {
        return notificacaoRepository.findByUsuarioIdOrderByDataEnvioDesc(usuarioId);
    }


    public List<Notificacao> buscarNaoLidas(UUID usuarioId) {
        return notificacaoRepository.findByUsuarioIdAndLidoFalseOrderByDataEnvioDesc(usuarioId);
    }


    public Long contarNaoLidas(UUID usuarioId) {
        return notificacaoRepository.countByUsuarioIdAndLidoFalse(usuarioId);
    }



    @Transactional
    public void marcarComoLida(UUID notificacaoId) {
        Notificacao notif = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));

        notif.setLido(true);
        notif.setDataLeitura(Instant.now());
        notificacaoRepository.save(notif);
    }


    @Transactional
    public void marcarTodasLidas(UUID usuarioId) {
        List<Notificacao> naoLidas = buscarNaoLidas(usuarioId);
        Instant agora = Instant.now();

        for (Notificacao notif : naoLidas) {
            notif.setLido(true);
            notif.setDataLeitura(agora);
        }

        notificacaoRepository.saveAll(naoLidas);
    }


    @Transactional
    public void excluir(UUID notificacaoId) {
        notificacaoRepository.deleteById(notificacaoId);
    }



    @Transactional
    public MensagemSistema criarMensagemSistema(String titulo, String conteudo,
                                                String tipo, String prioridade,
                                                Boolean exibirPopup) {
        MensagemSistema msg = new MensagemSistema();
        msg.setTitulo(titulo);
        msg.setConteudo(conteudo);
        msg.setTipo(tipo);
        msg.setPrioridade(prioridade);
        msg.setExibirPopup(exibirPopup);
        msg.setAtivo(true);

        return mensagemRepository.save(msg);
    }


    public List<MensagemSistema> buscarMensagensAtivas() {
        return mensagemRepository.findByAtivoTrueOrderByDataInicioDesc();
    }


    public List<MensagemSistema> buscarMensagensPopup() {
        return mensagemRepository.findMensagensPopup(Instant.now());
    }


    public List<MensagemSistema> buscarMensagensVigentes() {
        return mensagemRepository.findMensagensVigentes(Instant.now());
    }


    @Transactional
    public void desativarMensagem(UUID mensagemId) {
        MensagemSistema msg = mensagemRepository.findById(mensagemId)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
        msg.setAtivo(false);
        mensagemRepository.save(msg);
    }


    @Transactional
    public PreferenciasNotificacao salvarPreferencias(UUID usuarioId, PreferenciasNotificacao prefs) {
        Optional<PreferenciasNotificacao> existente = preferenciasRepository.findByUsuarioId(usuarioId);

        if (existente.isPresent()) {
            PreferenciasNotificacao p = existente.get();
            p.setEmailAtivo(prefs.getEmailAtivo());
            p.setNotifNovaAula(prefs.getNotifNovaAula());
            p.setNotifCancelamento(prefs.getNotifCancelamento());
            p.setNotifPagamento(prefs.getNotifPagamento());
            p.setNotifAvaliacao(prefs.getNotifAvaliacao());
            return preferenciasRepository.save(p);
        } else {
            prefs.setUsuarioId(usuarioId);
            return preferenciasRepository.save(prefs);
        }
    }


    public PreferenciasNotificacao buscarPreferencias(UUID usuarioId) {
        return preferenciasRepository.findByUsuarioId(usuarioId)
                .orElse(new PreferenciasNotificacao(usuarioId));
    }


    private boolean deveEnviar(UUID usuarioId, String tipo) {
        Optional<PreferenciasNotificacao> prefsOpt = preferenciasRepository.findByUsuarioId(usuarioId);

        if (prefsOpt.isEmpty()) {
            return true;
        }

        PreferenciasNotificacao prefs = prefsOpt.get();

        switch (tipo) {
            case "NOVA_AULA":
                return prefs.getNotifNovaAula();
            case "CANCELAMENTO":
                return prefs.getNotifCancelamento();
            case "PAGAMENTO":
                return prefs.getNotifPagamento();
            case "AVALIACAO":
                return prefs.getNotifAvaliacao();
            default:
                return true;
        }
    }


    private void simularEnvioEmail(Notificacao notif) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║      ENVIO DE EMAIL                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ ID: " + notif.getId());
        System.out.println("║ Para Usuário: " + notif.getUsuarioId());
        System.out.println("║ Tipo: " + notif.getTipo());
        System.out.println("║ Prioridade: " + notif.getPrioridade());
        System.out.println("║ Título: " + notif.getTitulo());
        System.out.println("║ Mensagem: " + notif.getMensagem());
        System.out.println("║ Data: " + (notif.getDataEnvio() != null ? notif.getDataEnvio() : "Agora"));
        System.out.println("╚════════════════════════════════════════╝\n");
    }
}