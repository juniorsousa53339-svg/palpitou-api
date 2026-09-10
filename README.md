# Palpitou API

API REST para gerenciamento de bolões de futebol, desenvolvida com Java e Spring Boot.

O projeto permite estruturar campeonatos, jogos, times, bolões, usuários, participações, pagamentos, palpites e premiações, além de implementar regras de negócio relacionadas à participação em bolões e cálculo de pontuação.

## Status do Projeto

**Em desenvolvimento — Back-end**

A estrutura principal da API REST está implementada e atualmente o projeto está concentrado na evolução das regras de negócio, segurança e testes.

### Implementado

* [x] Estrutura inicial da aplicação
* [x] Modelagem das entidades
* [x] DTOs de entrada e saída
* [x] Mappers
* [x] Repositories
* [x] Services
* [x] Controllers REST
* [x] CRUD de campeonatos
* [x] CRUD de bolões
* [x] CRUD de jogos
* [x] CRUD de times
* [x] CRUD de usuários
* [x] CRUD de pagamentos
* [x] CRUD de participações
* [x] CRUD de palpites
* [x] Consulta e exclusão de premiações
* [x] Validações de regras de negócio
* [x] Cálculo de pontuação
* [x] Geração de ranking por rodada
* [x] Configuração inicial do Spring Security
* [x] Banco H2 para desenvolvimento
* [x] Documentação com OpenAPI / Swagger

### Em desenvolvimento

* [ ] Autenticação e autorização
* [ ] Testes das regras de negócio
* [ ] Tratamento global de exceções
* [ ] Integração com API externa de futebol
* [ ] Persistência com PostgreSQL
* [ ] Docker
* [ ] Front-end com Angular

---

## Sobre o Projeto

O **Palpitou** é uma API REST para gerenciamento de bolões de futebol.

A aplicação foi estruturada para separar responsabilidades entre controllers, services, repositories, entidades, DTOs e mappers.

O fluxo principal do domínio é representado pelas seguintes entidades:

```text
Campeonato
    |
    +-- Jogos
    |     |
    |     +-- Time mandante
    |     +-- Time visitante
    |
    +-- Bolões
          |
          +-- Participações
          |     |
          |     +-- Pagamento
          |
          +-- Palpites
          |
          +-- Premiações
```

O sistema também possui uma camada específica para cálculo de pontuação e geração de ranking por rodada.

---

## Funcionalidades

### Campeonatos

A API possui operações para:

* cadastrar campeonato;
* listar campeonatos;
* buscar campeonato por ID;
* atualizar campeonato;
* excluir campeonato.

O campeonato possui informações como nome, temporada e status.

---

### Bolões

A API permite:

* cadastrar bolões;
* associar um bolão a um campeonato;
* definir valor de inscrição;
* definir período de participação;
* controlar o status do bolão;
* listar bolões;
* buscar bolão por ID;
* atualizar bolão;
* excluir bolão.

---

### Jogos

A API permite:

* cadastrar jogos;
* associar jogos a campeonatos;
* definir rodada;
* definir data e horário da partida;
* definir time mandante;
* definir time visitante;
* registrar resultado;
* controlar o status do jogo;
* listar jogos;
* buscar jogo por ID;
* atualizar jogo;
* excluir jogo.

Também existem validações relacionadas ao cadastro de partidas, como impedir que o mesmo time seja utilizado como mandante e visitante.

---

### Times

A API possui operações para:

* cadastrar times;
* listar times;
* buscar time por ID;
* atualizar times;
* excluir times.

Cada time possui informações como nome, sigla e escudo.

---

### Usuários

A API possui operações para:

* cadastrar usuários;
* listar usuários;
* buscar usuário por ID;
* atualizar dados do usuário;
* atualizar dados administrativos;
* excluir usuário.

Os usuários possuem um perfil definido por `Role`, permitindo a evolução do sistema para diferentes níveis de acesso.

---

### Participações

A entidade `Participacao` representa a participação de um usuário em um bolão.

A API permite:

* cadastrar participação;
* listar participações;
* buscar participação por ID;
* atualizar participação;
* excluir participação.

Existem regras de negócio para validar a participação, incluindo:

* impedir participação duplicada no mesmo bolão;
* verificar o status do pagamento;
* verificar se o bolão já foi iniciado.

---

### Pagamentos

A entidade `Pagamento` representa o pagamento relacionado à participação no bolão.

A API permite:

* cadastrar pagamento;
* listar pagamentos;
* buscar pagamento por ID;
* atualizar o status do pagamento;
* excluir pagamento.

O pagamento possui informações relacionadas ao usuário, bolão, valor, nome do pagador via Pix, comprovante e status.

---

### Palpites

A entidade `Palpite` representa a previsão de um usuário para determinado jogo.

A API permite:

* cadastrar palpite;
* listar palpites;
* buscar palpite por ID;
* atualizar os pontos obtidos;
* excluir palpite.

O sistema possui regras para:

* impedir mais de um palpite do mesmo usuário para o mesmo jogo;
* impedir palpites após o início da partida.

---

## Pontuação

O projeto possui um serviço específico responsável pelo cálculo da pontuação dos palpites.

A regra implementada atualmente é:

| Situação                                     | Pontos |
| -------------------------------------------- | -----: |
| Placar exato                                 |     10 |
| Vencedor correto + gols do vencedor corretos |      7 |
| Vencedor correto                             |      5 |
| Empate previsto corretamente                 |      5 |
| Resultado incorreto                          |      0 |

O cálculo é realizado com base no resultado do jogo e no palpite realizado pelo usuário.

Exemplo:

```text
Resultado:
Flamengo 2 x 1 Palmeiras

Palpite:
Flamengo 2 x 1 Palmeiras

Resultado: 10 pontos
```

Outro exemplo:

```text
Resultado:
Flamengo 2 x 1 Palmeiras

Palpite:
Flamengo 3 x 1 Palmeiras

Resultado: 7 pontos
```

O `PontuacaoService` também possui métodos para:

* buscar palpites de uma rodada;
* buscar palpites de um usuário em uma rodada;
* identificar participantes de uma rodada;
* calcular a pontuação dos participantes;
* gerar o ranking da rodada.

---

## Arquitetura

O projeto utiliza uma arquitetura em camadas, separando responsabilidades dentro da aplicação.

```text
src/main/java/br/com/palpitou

├── controller
├── dto
├── entity
├── enums
├── mapper
├── repository
├── security
└── service
```

### Controller

Responsável pela exposição dos endpoints REST.

Controllers atuais:

```text
BolaoController
CampeonatoController
JogoController
PagamentoController
PalpiteController
ParticipacaoController
PremiacaoController
TimeController
UserController
```

Os controllers recebem as requisições HTTP, realizam as validações dos DTOs e delegam o processamento para os services.

---

### Service

Responsável pelas regras de negócio da aplicação.

Entre as responsabilidades implementadas estão:

* validação de entidades relacionadas;
* prevenção de registros duplicados;
* validação de datas;
* validação de status;
* validação de pagamento;
* controle de participação;
* controle de palpites;
* cálculo de pontuação;
* geração de ranking.

---

### Repository

Responsável pelo acesso aos dados através do **Spring Data JPA**.

Os repositories abstraem as operações de persistência e também permitem consultas específicas utilizadas pelas regras de negócio.

---

### Entity

Representa as entidades do domínio da aplicação e seu mapeamento para o banco de dados utilizando JPA/Hibernate.

Principais entidades:

```text
User
Campeonato
Bolao
Jogo
Time
Participacao
Pagamento
Palpite
Premiacao
```

---

### DTO

A aplicação utiliza DTOs para entrada e saída de dados.

Exemplos:

```text
Request
Response
PutRequest
PutResponse
```

Essa separação evita que as entidades sejam utilizadas diretamente como contrato da API.

---

### Mapper

Os mappers são responsáveis pela conversão entre entidades e DTOs.

Exemplos:

```text
UserMapper
CampeonatoMapper
BolaoMapper
JogoMapper
TimeMapper
ParticipacaoMapper
PagamentoMapper
PalpiteMapper
PremiacaoMapper
RankingMapper
```

---

### Security

O projeto possui a configuração inicial do **Spring Security**.

A camada está preparada para a implementação das regras de autenticação e autorização da aplicação.

A implementação completa de controle de acesso ainda faz parte do desenvolvimento.

---

## Tecnologias

| Categoria      | Tecnologia                  |
| -------------- | --------------------------- |
| Linguagem      | Java 21                     |
| Framework      | Spring Boot 4.1.0           |
| API REST       | Spring Web MVC              |
| Persistência   | Spring Data JPA             |
| ORM            | Hibernate                   |
| Validação      | Jakarta Bean Validation     |
| Segurança      | Spring Security             |
| Banco de dados | H2                          |
| Documentação   | Springdoc OpenAPI / Swagger |
| Produtividade  | Lombok                      |
| Build          | Maven                       |
| Versionamento  | Git / GitHub                |

---

## Endpoints

Os principais recursos disponíveis atualmente são:

| Recurso       | Endpoint         | Operações              |
| ------------- | ---------------- | ---------------------- |
| Campeonatos   | `/campeonatos`   | GET, POST, PUT, DELETE |
| Bolões        | `/boloes`        | GET, POST, PUT, DELETE |
| Jogos         | `/jogos`         | GET, POST, PUT, DELETE |
| Times         | `/times`         | GET, POST, PUT, DELETE |
| Usuários      | `/users`         | GET, POST, PUT, DELETE |
| Pagamentos    | `/pagamentos`    | GET, POST, PUT, DELETE |
| Participações | `/participacoes` | GET, POST, PUT, DELETE |
| Palpites      | `/palpites`      | GET, POST, PUT, DELETE |
| Premiações    | `/premiacao`     | GET, DELETE            |

### Padrões utilizados

Busca por ID:

```http
GET /recurso/{id}
```

Criação:

```http
POST /recurso
```

Atualização:

```http
PUT /recurso/{id}
```

Exclusão:

```http
DELETE /recurso/{id}
```

Os endpoints utilizam `Long` como identificador das entidades.

---

## Validações de Negócio

### Participação

* Um usuário não pode participar duas vezes do mesmo bolão.
* O pagamento precisa estar aprovado para a participação.
* Não é possível participar de um bolão após seu início.

### Palpite

* Um usuário não pode cadastrar mais de um palpite para o mesmo jogo.
* Não é possível realizar um palpite depois do início da partida.

### Jogo

* O jogo deve estar associado a um campeonato.
* O time mandante e o time visitante não podem ser iguais.
* Existem validações relacionadas ao status do campeonato.

### Bolão

* O bolão deve estar associado a um campeonato.
* Existem validações relacionadas ao status do bolão e ao período de participação.

---

## Banco de Dados

Atualmente o projeto utiliza **H2 em memória** para desenvolvimento.

Configuração atual:

```text
Banco: H2
URL: jdbc:h2:mem:palpitou
Usuário: sa
Senha: sem senha
Console: /h2-console
```

Configuração JPA/Hibernate:

```text
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

A aplicação poderá posteriormente utilizar PostgreSQL para persistência em ambiente de produção.

---

## Documentação da API

O projeto utiliza **Springdoc OpenAPI** para geração da documentação da API.

A documentação pode ser utilizada através do Swagger UI após a aplicação ser iniciada.

---

## Como Executar

### Pré-requisitos

* Java 21
* Maven

### Clonar o repositório

```bash
git clone https://github.com/juniorsousa53339-svg/palpitou-api.git
cd palpitou-api
```

### Executar com Maven Wrapper

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A aplicação será iniciada utilizando a configuração presente em `application.properties`.

---

## Estrutura de Desenvolvimento

O projeto utiliza branches para separar o desenvolvimento das funcionalidades:

```text
main
 │
 └── Código estável
     
develop
 │
 ├── feature/*
 ├── release/*
 └── hotfix/*
```

### Branches

| Branch      | Finalidade                         |
| ----------- | ---------------------------------- |
| `main`      | Código estável                     |
| `develop`   | Desenvolvimento principal          |
| `feature/*` | Desenvolvimento de funcionalidades |
| `release/*` | Preparação de versões              |
| `hotfix/*`  | Correções específicas              |

Exemplo:

```bash
git checkout develop

git checkout -b feature/nova-funcionalidade

# Desenvolvimento

git checkout develop
git merge feature/nova-funcionalidade
```

---

## Roadmap

### Back-end

* [x] Modelagem das entidades
* [x] DTOs
* [x] Mappers
* [x] Repositories
* [x] Services
* [x] Controllers
* [x] CRUDs principais
* [x] Regras de negócio iniciais
* [x] Cálculo de pontuação
* [x] Ranking por rodada
* [x] Configuração inicial do Spring Security
* [x] OpenAPI
* [ ] Autenticação
* [ ] Autorização por perfil
* [ ] Tratamento global de exceções
* [ ] Ampliação dos testes automatizados
* [ ] Integração com API externa de futebol
* [ ] PostgreSQL

### Front-end

* [ ] Estrutura Angular
* [ ] Autenticação
* [ ] Área do participante
* [ ] Área administrativa
* [ ] Campeonatos
* [ ] Bolões
* [ ] Pagamentos
* [ ] Palpites
* [ ] Ranking
* [ ] Premiações
* [ ] Dashboard

### Infraestrutura

* [ ] Docker
* [ ] Docker Compose
* [ ] Configuração de ambientes
* [ ] Deploy da API
* [ ] Deploy do banco
* [ ] CI/CD

---

## Objetivo

O objetivo do Palpitou é evoluir de uma API REST de gerenciamento de bolões para uma plataforma completa de bolões de futebol.

O projeto está sendo desenvolvido com foco em:

* desenvolvimento de APIs REST;
* organização em camadas;
* separação de responsabilidades;
* modelagem de domínio;
* persistência com JPA;
* validação de regras de negócio;
* processamento de pontuação;
* geração de rankings;
* segurança da aplicação;
* integração futura com serviços externos;
* desenvolvimento de um front-end para consumo da API.

---

## Autor

Desenvolvido por **Luciano Junior**

[LinkedIn](https://www.linkedin.com/in/lucianosousa001/) • [GitHub](https://github.com/juniorsousa53339-svg)

---

## Licença

Este projeto está sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.
