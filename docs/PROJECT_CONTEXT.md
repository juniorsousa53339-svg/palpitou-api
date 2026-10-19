
# Contexto do Projeto — Palpitou API

Documento gerado para registrar o estado atual da arquitetura, escopo funcional, tecnologias, regras de negócio e pendências técnicas do projeto **Palpitou API**, servindo como fonte de verdade para continuidade de desenvolvimento.

---

## 1. Visão Geral

- **Propósito:** Aplicação backend para gestão de bolões de futebol, com foco inicial no Campeonato Brasileiro e no Campeonato Paulista.
- **Público-alvo:** Usuários organizadores e participantes de bolões de futebol em grupos de amigos, bolões corporativos ou plataformas de palpites.
- **Funcionalidades previstas:** Cadastro de usuários, times, campeonatos, jogos, criação e gestão de bolões, inscrições/participações com comprovação de pagamento (PIX), envio de palpites, cálculo automatizado de pontuação e ranking, além de premiações e dashboard consolidado.

---

## 2. Escopo Funcional

- **Usuários (`User`):** Cadastro de participantes/administradores com controle de perfil (`Role`).
- **Times (`Time`):** Cadastro de clubes de futebol (nome, escudo, sigla).
- **Campeonatos (`Campeonato`):** Gestão de campeonatos por temporada (ex: Brasileirão, Paulistão) e status global.
- **Jogos (`Jogo`):** Partidas vinculadas a um campeonato, envolvendo mandante, visitante, data/hora, rodada, gols e status global.
- **Bolões (`Bolao`):** Grupos de bolões associados a um campeonato, com valor de inscrição, data de início/fim, status, participações e premiações.
- **Participações (`Participacao`):** Inscrição de usuários em bolões, associada a status de participação e comprovação de pagamento.
- **Pagamentos (`Pagamento`):** Registro de pagamentos via PIX (valor, status, nome do pagador, comprovante) vinculados ao usuário e ao bolão.
- **Palpites (`Palpite`):** Palpites de placares (gols mandante e visitante) enviados por usuários para partidas específicas, registrando pontos obtidos.
- **Premiações (`Premiacao`):** Definição de premiações por posição/valor/descrição em bolões.
- **Pontuação e Ranking (`PontuacaoService` / `RankingResponse`):** Cálculo automatizado de pontos por partida e geração de rankings por rodada/bolão.
- **Dashboard (`DashboardController` / `DashboardService`):** Visão consolidada de estatísticas e bolões.

---

## 3. Arquitetura Atual

A aplicação segue uma arquitetura em camadas padrão do Spring Boot:

- **Controller (`br.com.palpitou.controller`):** Exposição dos endpoints REST para todas as entidades e recursos (`BolaoController`, `CampeonatoController`, `DashboardController`, `JogoController`, `PagamentoController`, `PalpiteController`, `ParticipacaoController`, `PremiacaoController`, `TimeController`, `UserController`).
- **Service (`br.com.palpitou.service`):** Camada de regras de negócio (`BolaoService`, `CampeonatoService`, `DashboardService`, `JogoService`, `PagamentoService`, `PalpiteService`, `ParticipacaoService`, `PontuacaoService`, `PremiacaoService`, `TimeService`, `UserService`).
- **Repository (`br.com.palpitou.repository`):** Interfaces Spring Data JPA para persistência (`JpaRepository`).
- **Entity (`br.com.palpitou.entity`):** Entidades JPA mapeando tabelas relacionais (`User`, `Time`, `Campeonato`, `Jogo`, `Bolao`, `Participacao`, `Pagamento`, `Palpite`, `Premiacao`).
- **DTO / Mapper (`br.com.palpitou.dto` e `br.com.palpitou.mapper`):** Objetos de transferência de dados (Request, Response, PutRequest, PutResponse, Dashboard) e mappers.
- **Exception (`br.com.palpitou.exception`):** Tratamento global de exceções (`GlobalExceptionHandler`, `BusinessRuleException`, `ResourceNotFoundException`).
- **Security (`br.com.palpitou.security`):** Configuração do Spring Security (`SecurityConfig`).

---

## 4. Tecnologias

Confirmado no `pom.xml` e no código:
- **Java:** 17
- **Spring Boot:** 4.1.0
- **Spring Web / WebMVC**
- **Spring Data JPA**
- **Spring Security**
- **Spring Validation**
- **Lombok**
- **Banco de Dados:** H2 Database (em memória, modo `update`)
- **Documentação API:** SpringDoc OpenAPI / Swagger UI (versão `2.8.9`)
- **Build / Gerenciamento:** Maven
- **Testes:** JUnit 5 / Spring Boot Test starters (`spring-boot-starter-data-jpa-test`, `spring-boot-starter-security-test`, `spring-boot-starter-validation-test`, `spring-boot-starter-webmvc-test`)

---

## 5. Estado Atual (O que está implementado)

- **Estrutura Completa de CRUDs:** Controllers, services, repositories, entidades e DTOs implementados para Usuários, Times, Campeonatos, Jogos, Bolões, Participações, Pagamentos, Palpites e Premiações.
- **Dashboard:** Endpoints `/dashboard` e `/dashboard/boloes` funcionais.
- **Cálculo de Pontuação (`PontuacaoService`):** Lógica implementada para pontuar palpites com base em regras de placar exato, vencedor com gols exatos, vencedor simples e empate.
- **Testes Unitários de Serviços:**
  - `BolaoServiceTest`
  - `DashboardServiceTest`
  - `PagamentoServiceTest`
  - `PalpiteServiceTest`
  - `ParticipacaoServiceTest`
  - `PontuacaoServiceTest`
  - `ApiApplicationTests`

---

## 6. Regras de Negócio Confirmadas e Pendentes

### Confirmadas (por implementação):
- **Pontuação por Palpite (`PontuacaoService`):**
  - Placar exato: **10 pontos**.
  - Vencedor correto + gols do mandante/visitante corretos: **7 pontos**.
  - Vencedor correto (mandante ou visitante): **5 pontos**.
  - Empate previsto corretamente: **5 pontos**.
  - Outros cenários: **0 pontos**.
- **Validação de Inscrição/Pagamento:**
  - Verificação de pagamento aprovado (`Pagamento.isPagamentoAprovado`) para participação.
- **Validações de Campos:**
  - Uso de `@NotBlank`, `@NotNull`, `@Email` nas entidades e DTOs validados via Spring Validation (`@Valid`).

### A Confirmar / Pendentes de Negócio:
- Critérios de desempate no ranking geral do bolão.
- Regras para alteração ou exclusão de palpites após o início da partida (*a confirmar*).
- Regras de distribuição automática de premiações (*a confirmar*).

---

## 7. Pendências Técnicas

- **Segurança (Spring Security):**
  - Atualmente a configuração (`SecurityConfig`) desativa CSRF e permite todas as requisições (`anyRequest().permitAll()`). Necessário implementar autenticação JWT/Session, criptografia de senhas (BCrypt) e controle de acesso baseado em papéis (`Role`).
- **Persistência:**
  - Atualmente utiliza H2 Database em memória (`jdbc:h2:mem:palpitou`). Necessário configurar suporte a banco relacional persistente (PostgreSQL/MySQL) para ambiente de produção.
- **Validação de Erros:**
  - Tratamento de exceções consolidado via `GlobalExceptionHandler`, mas necessita revisão de códigos de status HTTP e mensagens de erro padronizadas.
- **Testes:**
  - Existem testes unitários para a camada de serviço, mas faltam testes de integração para controllers e repositórios.

---

## 8. Roadmap Proposto

### Fase 1: Fundação e Estrutura Básica (Concluído)
- Configuração do project, entidades principais, repositories, serviços e DTOs de todas as entidades.

### Fase 2: Lógica de Negócio e Testes Unitários (Concluído / Em andamento)
- Implementação do motor de pontuação (`PontuacaoService`), dashboard e testes unitários de serviços.

### Fase 3: Segurança e Autenticação (Planejado / Pendente)
- Implementação de autenticação (JWT), criptografia de senhas e autorização por perfis.

### Fase 4: Persistência Externa e Integrações (Planejado)
- Configuração de banco de dados persistente (PostgreSQL) e preparação para consumo de APIs de resultados de futebol externos.

### Fase 5: Testes de Integração e Documentação Final (Planejado)
- Cobertura de testes de integração, testes end-to-end e refinamento da documentação OpenAPI/Swagger.

---

## 9. Decisões e Cuidados

- **Padrões de Código:** Manter a separação clara entre Entities, DTOs (Request/Response/Put) e Services. Respeitar as anotações do Lombok (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@RequiredArgsConstructor`).
- **Tratamento de Exceções:** Utilizar `BusinessRuleException` para violações de regras de negócio e `ResourceNotFoundException` para recursos não encontrados.
- **Proteção do Repositório:** Não alterar arquivos fora do escopo, preservar alterações locais não commitadas e nunca expor credenciais ou segredos.
