# Decisões Arquiteturais (ADRs) — Palpitou API

Este documento registra as decisões técnicas e arquiteturais tomadas no decorrer do projeto Palpitou API, incluindo o contexto, a decisão, a justificativa e as consequências.

---

## ADR-001: Adoção de Java 17 e Spring Boot 4.1.0 com Arquitetura em Camadas

- **Contexto:** Necessidade de construir um backend robusto, escalável e de fácil manutenção para gerenciamento de bolões de futebol, utilizando tecnologias modernas e amplamente adotadas no ecossistema Java.
- **Decisão:** Adotar Java 17, Spring Boot 4.1.0 e uma arquitetura em camadas tradicional (Controller, Service, Repository, Entity, DTO, Mapper).
- **Justificativa:** 
  - O Java 17 oferece suporte de longo prazo (LTS), recursos modernos da linguagem e excelente estabilidade.
  - O Spring Boot acelera o desenvolvimento com configuração baseada em convenção sobre configuração.
  - A separação estrita em camadas garante baixo acoplamento, alta coesão e facilidade nos testes unitários e de integração.
- **Consequências:** Código estruturado, previsível e adequado para o nível de aprendizado e evolução do desenvolvedor.

---

## ADR-002: Utilização Inicial de H2 Database em Memória

- **Contexto:** Na fase inicial de desenvolvimento e testes, é necessário agilizar a execução da aplicação sem dependência de infraestrutura externa de banco de dados.
- **Decisão:** Utilizar o H2 Database em modo em memória (`jdbc:h2:mem:palpitou`) com atualização automática de schema JPA (`ddl-auto=update`).
- **Justificativa:** Facilita a execução rápida de testes locais, desenvolvimento autônomo e validação de regras de negócio sem fricção de configuração.
- **Consequências:** Os dados são perdidos ao reiniciar a aplicação. O projeto prevê a migração para PostgreSQL na Fase 4 do roadmap para persistência definitiva.

---

## ADR-003: Motor de Pontuação Centralizado em `PontuacaoService`

- **Contexto:** O cálculo dos pontos obtidos pelos participantes com base em seus palpites e nos placares reais dos jogos é o núcleo de negócio da aplicação.
- **Decisão:** Isolar toda a lógica de cálculo de pontuação em um serviço dedicado (`PontuacaoService`).
- **Justificativa:** Centraliza as regras de negócio (placar exato, vencedor correto com gols, vencedor simples, empate), evitando duplicação de código nos controllers ou repositories e facilitando a criação de testes unitários específicos.
- **Consequências:** Regras claras, testáveis e fáceis de ajustar caso o regulamento do bolão sofra alterações futuras.

---

## ADR-004: Isolação de Regras de Negócio e Validações via Bean Validation

- **Contexto:** Garantir a integridade dos dados recebidos pela API antes de persistir ou processar operações.
- **Decisão:** Utilizar anotações do Spring Validation (`@Valid`, `@NotBlank`, `@NotNull`, `@Email`) e criar exceções personalizadas (`BusinessRuleException`, `ResourceNotFoundException`) tratadas centralmente por `GlobalExceptionHandler`.
- **Justificativa:** Padroniza as respostas de erro da API, evita dados corrompidos no banco de dados e mantém os controllers limpos de lógica de validação complexa.
- **Consequências:** Respostas HTTP consistentes (ex: 400 Bad Request, 404 Not Found, 422 Unprocessable Entity) para os clientes da API.
