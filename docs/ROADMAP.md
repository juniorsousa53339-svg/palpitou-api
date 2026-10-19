# Roadmap — Palpitou API

Este documento detalha o planejamento por fases para o desenvolvimento da API do Palpitou. Cada fase possui objetivos claros, critérios de conclusão verificáveis e o estado atual correspondente.

---

## Fase 1 — Fundação e Domínio (Concluído)

- **Objetivo:** Estabelecer a estrutura base do projeto, entidades principais, repositories, serviços, mappers e DTOs para todas as entidades do domínio.
- **Escopo:**
  - Usuários (`User`) e perfis (`Role`).
  - Times (`Time`) e Campeonatos (`Campeonato`).
  - Jogos (`Jogo`), Bolões (`Bolao`), Participações (`Participacao`).
  - Pagamentos via PIX (`Pagamento`), Palpites (`Palpite`), Premiações (`Premiacao`).
- **Estado atual:** Concluído. Estrutura em camadas implementada e validada com testes unitários em serviços.

---

## Fase 2 — Segurança e Autenticação (Planejado / Em andamento)

- **Objetivo:** Garantir a segurança da API, protegendo rotas, dados de usuários e transações.
- **Tarefas:**
  - Implementar criptografia segura de senhas com `BCrypt`.
  - Configurar autenticação baseada em JWT (JSON Web Token).
  - Definir controle de acesso baseado em papéis (`ROLE_ADMIN`, `ROLE_USER`).
  - Proteger endpoints administrativos e de usuários.
  - Validar autorização sobre recursos individuais (ex: apenas o dono altera seu palpite ou bolão).
  - Revisar e endurecer a configuração do Spring Security (substituindo o `permitAll()` atual).
- **Critérios de conclusão:**
  - Autenticação funcional via endpoint de login gerando token JWT.
  - Endpoints protegidos por roles e propriedade de recursos.
  - Testes de segurança cobrindo cenários autenticados e não autenticados.

---

## Fase 3 — Testes e Confiabilidade (Planejado)

- **Objetivo:** Elevar a cobertura de testes automatizados e garantir a robustez contra regressões.
- **Tarefas:**
  - Implementar testes de integração para controllers usando `MockMvc` / `WebMvcTest`.
  - Adicionar cenários negativos (validações inválidas, recursos não encontrados, violações de regras de negócio).
  - Testar fluxos ponta a ponta de cálculo de pontuação e ranking.
  - Garantir relatórios de execução de testes transparentes e verificáveis.
- **Critérios de conclusão:**
  - Suite de testes executando com sucesso no Maven (`mvn test`).
  - Cobertura relevante em controllers, services e regras de pontuação.

---

## Fase 4 — Persistência e Ambientes (Planejado)

- **Objetivo:** Preparar o backend para persistência durável em banco relacional de produção.
- **Tarefas:**
  - Configurar perfis de ambiente Spring (`dev`, `test`, `prod`).
  - Configurar conexão com banco de dados PostgreSQL.
  - Avaliar o uso de ferramentas de migração de banco de dados (Flyway ou Liquibase), se aplicável.
  - Validar funcionamento das entidades JPA com o novo SGBD.
- **Critérios de conclusão:**
  - Aplicação executando com PostgreSQL em ambiente de homologação/produção.
  - Dados persistidos corretamente entre reinicializações da aplicação.

---

## Fase 5 — Integração com API Externa de Futebol (Planejado)

- **Objetivo:** Sincronizar automaticamente campeonatos, times, partidas e resultados de fontes externas.
- **Tarefas:**
  - Selecionar fornecedor de API de futebol (ex: API-Football, Footapi, etc.).
  - Desenvolver cliente HTTP isolado (usando `RestClient` ou `WebClient`) na camada de infraestrutura/serviço.
  - Mapear dados externos para as entidades do domínio (`Campeonato`, `Time`, `Jogo`).
  - Implementar rotinas de sincronização idempotentes para evitar duplicidades.
  - Automatizar a atualização de status e placares de partidas encerradas.
  - Tratar falhas de rede, limites de requisições (*rate limits*) e dados inconsistentes.
- **Critérios de conclusão:**
  - Sincronização automatizada ou sob demanda funcionando com respostas simuladas e reais.
  - Testes unitários e de integração para o cliente HTTP e mappers de sincronização.

---

## Fase 6 — Integração com Frontend (Planejado)

- **Objetivo:** Disponibilizar a API para consumo por aplicações cliente (web/mobile).
- **Tarefas:**
  - Revisar documentação OpenAPI / Swagger (`/swagger-ui.html`) para garantir clareza nos contratos.
  - Ajustar CORS para permitir requisições seguras do frontend.
  - Validar contratos de DTOs de Request e Response para todas as funcionalidades principais (login, bolões, palpites, ranking, dashboard).
- **Critérios de conclusão:**
  - Endpoints testados via Swagger e clientes REST (Postman/Insomnia/Frontend).

---

## Fase 7 — Deploy, DevOps e Produção (Planejado)

- **Objetivo:** Empacotar, containerizar e disponibilizar a aplicação de forma segura e monitorada.
- **Tarefas:**
  - Criar `Dockerfile` otimizado para a aplicação Spring Boot em Java 17.
  - Configurar `docker-compose` para subir a API junto com o banco PostgreSQL.
  - Gerenciar variáveis de ambiente e segredos de forma segura (`.env`, secrets manager).
  - Configurar logs estruturados e monitoramento básico.
  - Executar auditoria final de segurança antes da disponibilização pública.
- **Critérios de conclusão:**
  - Aplicação containerizada executando com sucesso via Docker.
  - Checklist de segurança aprovado.
