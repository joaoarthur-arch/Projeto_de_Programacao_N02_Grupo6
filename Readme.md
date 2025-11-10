# 🔔 Sistema de Notificações - Grupo 6 (SIMPLIFICADO)

## 📋 O que tem aqui?

Sistema simples de notificações para a Plataforma Véridia com:
- 2 tabelas principais: `notificacao` e `mensagem_sistema`
- 1 tabela extra (pontos): `preferencias_notificacao`
- API REST completa
- Interface visual HTML/JS
- Simulação de envio de emails

---

## 🚀 Como Usar

### 1. Configure o Supabase

1. Entre no seu Supabase
2. Vá em **SQL Editor**
3. Cole todo o conteúdo do arquivo `schema-simples.sql`
4. Execute

### 2. Configure o `application.properties`

Crie/edite o arquivo `src/main/resources/application.properties`:

```properties
# Supabase Connection
spring.datasource.url=jdbc:postgresql://SEU-HOST.supabase.co:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=SUA-SENHA

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server
server.port=8080
```

**Onde encontrar os dados do Supabase:**
- Vá em **Settings** → **Database**
- Copie o **Connection String** e extraia:
    - Host: `SEU-HOST.supabase.co`
    - Password: sua senha do projeto

### 3. Adicione as Dependências

Cole o conteúdo do `pom.xml` fornecido no seu arquivo pom.xml

### 4. Estrutura de Pastas

Coloque os arquivos assim:

```
src/main/java/com/Veridia/CidadeVeridiaOficial/
├── model/
│   ├── Notificacao.java
│   ├── MensagemSistema.java
│   └── PreferenciasNotificacao.java
├── repository/
│   ├── NotificacaoRepository.java
│   ├── MensagemSistemaRepository.java
│   └── PreferenciasNotificacaoRepository.java
├── service/
│   └── NotificacaoService.java
└── controller/
    └── NotificacaoController.java
```

### 5. Execute o Projeto

```bash
mvn clean install
mvn spring-boot:run
```

### 6. Configure a Interface

1. Abra o arquivo `notificacoes.html`
2. Altere a linha 312:
```javascript
const USUARIO_ID = 'SEU-UUID-AQUI'; // Coloque um UUID de usuário válido
```
3. Abra o arquivo no navegador

---

## 📡 Endpoints da API

### Notificações

```http
# Enviar notificação
POST /api/notificacoes/enviar
{
  "titulo": "Nova Aula",
  "mensagem": "Aula de Java às 14h",
  "tipo": "NOVA_AULA",
  "prioridade": "MEDIA",
  "usuarioId": "uuid-do-usuario"
}

# Buscar notificações do usuário
GET /api/notificacoes/usuario/{usuarioId}

# Buscar não lidas
GET /api/notificacoes/usuario/{usuarioId}/nao-lidas

# Contar não lidas
GET /api/notificacoes/usuario/{usuarioId}/contador

# Marcar como lida
PATCH /api/notificacoes/{id}/marcar-lida

# Marcar todas como lidas
PATCH /api/notificacoes/usuario/{usuarioId}/marcar-todas-lidas

# Excluir
DELETE /api/notificacoes/{id}
```

### Mensagens do Sistema

```http
# Criar mensagem
POST /api/notificacoes/mensagem-sistema
{
  "titulo": "Manutenção",
  "conteudo": "Sistema em manutenção domingo",
  "tipo": "MANUTENCAO",
  "prioridade": "URGENTE",
  "exibirPopup": true
}

# Buscar ativas
GET /api/notificacoes/mensagem-sistema/ativas

# Buscar popup
GET /api/notificacoes/mensagem-sistema/popup

# Buscar vigentes
GET /api/notificacoes/mensagem-sistema/vigentes

# Desativar
PATCH /api/notificacoes/mensagem-sistema/{id}/desativar
```

### Preferências (EXTRA)

```http
# Salvar preferências
POST /api/notificacoes/preferencias/{usuarioId}
{
  "emailAtivo": true,
  "notifNovaAula": true,
  "notifCancelamento": true,
  "notifPagamento": true,
  "notifAvaliacao": true
}

# Buscar preferências
GET /api/notificacoes/preferencias/{usuarioId}
```

---

## 🎯 Tipos de Notificação

- `NOVA_AULA` - Nova aula agendada
- `CANCELAMENTO` - Aula cancelada
- `PAGAMENTO` - Relacionado a pagamentos
- `AVALIACAO` - Solicitação de avaliação
- `MENSAGEM` - Mensagem geral

## 🎨 Tipos de Mensagem do Sistema

- `AVISO` - Avisos gerais
- `MANUTENCAO` - Manutenção programada
- `ATUALIZACAO` - Atualizações do sistema
- `ALERTA` - Alertas importantes

## ⭐ Prioridades

- `BAIXA` - Não urgente
- `MEDIA` - Prioridade normal
- `URGENTE` - Requer atenção imediata

---

## 💡 Como Testar

### 1. Enviar Notificação via Postman/Insomnia

```http
POST http://localhost:8080/api/notificacoes/enviar
Content-Type: application/json

{
  "titulo": "Teste de Notificação",
  "mensagem": "Esta é uma mensagem de teste",
  "tipo": "MENSAGEM",
  "prioridade": "MEDIA",
  "usuarioId": "SEU-UUID-AQUI"
}
```

### 2. Ver o Email Simulado no Console

Quando você enviar uma notificação, verá no console do Spring Boot:

```
╔════════════════════════════════════════╗
║     📧 SIMULAÇÃO DE ENVIO DE EMAIL    ║
╠════════════════════════════════════════╣
║ ID: abc123...
║ Para Usuário: uuid...
║ Tipo: NOVA_AULA
║ Prioridade: MEDIA
║ Título: Nova Aula
║ Mensagem: Aula de Java às 14h
║ Data: 2025-11-08T...
╚════════════════════════════════════════╝
```

### 3. Testar Preferências

```http
POST http://localhost:8080/api/notificacoes/preferencias/{usuarioId}
Content-Type: application/json

{
  "emailAtivo": true,
  "notifNovaAula": false,
  "notifCancelamento": true,
  "notifPagamento": true,
  "notifAvaliacao": true
}
```

Se `notifNovaAula` estiver `false`, notificações de tipo `NOVA_AULA` não serão enviadas!

---

## ✅ Requisitos Atendidos

### Obrigatórios:
- ✅ Criar tabelas `notificacao` e `mensagem_sistema`
- ✅ Implementar Repository com CRUD
- ✅ Registrar data, tipo e destinatário
- ✅ Relacionamento com usuário
- ✅ Interface visual (HTML/JS)
- ✅ Histórico de notificações com status (lido/não lido)
- ✅ Personalização por tipo de evento

### EXTRAS (+0,5):
- ✅ Simular envio de emails via console
- ✅ Preferências de notificação por usuário
- ✅ Log no console mostrando quem enviou, quando e para quem

---

## 🐛 Problemas Comuns

### Erro de Conexão com Supabase
- Verifique se as credenciais estão corretas
- Confira se o IP está na whitelist do Supabase

### "Usuário não encontrado"
- Certifique-se de que existe um usuário na tabela `usuarios`
- Use o UUID correto na interface

### CORS Error
- O `@CrossOrigin(origins = "*")` já está no controller
- Se persistir, adicione no `application.properties`:
```properties
spring.web.cors.allowed-origins=*
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,PATCH
```

---

## 📞 Suporte

Dúvidas? Verifique:
1. Console do Spring Boot (logs de erro)
2. Console do navegador (Network tab)
3. Supabase Dashboard (Query logs)

---

## 🎓 Grupo 6

Sistema desenvolvido para a disciplina de POO - Cidade de Véridia