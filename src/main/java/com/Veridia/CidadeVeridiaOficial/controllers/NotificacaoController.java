package com.Veridia.CidadeVeridiaOficial.controllers;

import com.Veridia.CidadeVeridiaOficial.model.Notificacao;
import com.Veridia.CidadeVeridiaOficial.model.MensagemSistema;
import com.Veridia.CidadeVeridiaOficial.model.PreferenciasNotificacao;
import com.Veridia.CidadeVeridiaOficial.Service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/notificacoes")
@CrossOrigin(origins = "*")
public class NotificacaoController {

    @Autowired
    private NotificacaoService service;

    @PostMapping("/enviar")
    public ResponseEntity<Notificacao> enviar(@RequestBody Map<String, Object> dados) {
        String titulo = (String) dados.get("titulo");
        String mensagem = (String) dados.get("mensagem");
        String tipo = (String) dados.get("tipo");
        String prioridade = dados.getOrDefault("prioridade", "MEDIA").toString();
        UUID usuarioId = UUID.fromString((String) dados.get("usuarioId"));

        Notificacao notif = service.enviarNotificacao(titulo, mensagem, tipo, prioridade, usuarioId);
        return ResponseEntity.ok(notif);
    }

    @PostMapping("/enviar-varios")
    public ResponseEntity<List<Notificacao>> enviarVarios(@RequestBody Map<String, Object> dados) {
        String titulo = (String) dados.get("titulo");
        String mensagem = (String) dados.get("mensagem");
        String tipo = (String) dados.get("tipo");
        String prioridade = dados.getOrDefault("prioridade", "MEDIA").toString();

        @SuppressWarnings("unchecked")
        List<String> usuariosIdsStr = (List<String>) dados.get("usuariosIds");
        List<UUID> usuariosIds = new ArrayList<>();
        for (String id : usuariosIdsStr) {
            usuariosIds.add(UUID.fromString(id));
        }

        List<Notificacao> notifs = service.enviarParaVarios(titulo, mensagem, tipo, prioridade, usuariosIds);
        return ResponseEntity.ok(notifs);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacao>> buscarPorUsuario(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(service.buscarPorUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/nao-lidas")
    public ResponseEntity<List<Notificacao>> buscarNaoLidas(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(service.buscarNaoLidas(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/contador")
    public ResponseEntity<Map<String, Long>> contarNaoLidas(@PathVariable UUID usuarioId) {
        Long count = service.contarNaoLidas(usuarioId);
        return ResponseEntity.ok(Map.of("naoLidas", count));
    }

    @PatchMapping("/{id}/marcar-lida")
    public ResponseEntity<Void> marcarLida(@PathVariable UUID id) {
        service.marcarComoLida(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/usuario/{usuarioId}/marcar-todas-lidas")
    public ResponseEntity<Void> marcarTodasLidas(@PathVariable UUID usuarioId) {
        service.marcarTodasLidas(usuarioId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.ok().build();
    }

    // MENSAGENS

    @PostMapping("/mensagem-sistema")
    public ResponseEntity<MensagemSistema> criarMensagem(@RequestBody Map<String, Object> dados) {
        String titulo = (String) dados.get("titulo");
        String conteudo = (String) dados.get("conteudo");
        String tipo = (String) dados.get("tipo");
        String prioridade = dados.getOrDefault("prioridade", "MEDIA").toString();
        Boolean exibirPopup = (Boolean) dados.getOrDefault("exibirPopup", false);

        MensagemSistema msg = service.criarMensagemSistema(titulo, conteudo, tipo, prioridade, exibirPopup);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/mensagem-sistema/ativas")
    public ResponseEntity<List<MensagemSistema>> buscarMensagensAtivas() {
        return ResponseEntity.ok(service.buscarMensagensAtivas());
    }

    @GetMapping("/mensagem-sistema/popup")
    public ResponseEntity<List<MensagemSistema>> buscarPopup() {
        return ResponseEntity.ok(service.buscarMensagensPopup());
    }

    @GetMapping("/mensagem-sistema/vigentes")
    public ResponseEntity<List<MensagemSistema>> buscarVigentes() {
        return ResponseEntity.ok(service.buscarMensagensVigentes());
    }

    @PatchMapping("/mensagem-sistema/{id}/desativar")
    public ResponseEntity<Void> desativarMensagem(@PathVariable UUID id) {
        service.desativarMensagem(id);
        return ResponseEntity.ok().build();
    }

    // EXTRA

    @PostMapping("/preferencias/{usuarioId}")
    public ResponseEntity<PreferenciasNotificacao> salvarPreferencias(
            @PathVariable UUID usuarioId,
            @RequestBody PreferenciasNotificacao prefs) {
        PreferenciasNotificacao salvas = service.salvarPreferencias(usuarioId, prefs);
        return ResponseEntity.ok(salvas);
    }

    @GetMapping("/preferencias/{usuarioId}")
    public ResponseEntity<PreferenciasNotificacao> buscarPreferencias(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(service.buscarPreferencias(usuarioId));
    }
}