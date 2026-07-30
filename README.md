# ⚽ Bolão Online — Sistema de Gerenciamento de Bolões de Futebol

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![Angular](https://img.shields.io/badge/Angular-17-red?logo=angular)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Container-2496ED?logo=docker)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

> Plataforma web completa para gerenciamento de bolões de futebol, com integração a uma API de futebol para importação automática de campeonatos, jogos e resultados, cálculo de pontuação e ranking em tempo real.

---

## 🚧 Status do Projeto

**Em desenvolvimento** — atualmente o foco está 100% no **back-end**. O front-end (Angular) ainda não foi iniciado.

- [x] Definição do escopo e regras de negócio
- [x] Modelagem do banco de dados
- [ ] API REST (Spring Boot) — *em andamento*
- [ ] Integração com API externa de futebol
- [ ] Autenticação e autorização (Spring Security)
- [ ] Front-end (Angular) — *não iniciado*
- [ ] Dashboard financeiro do administrador
- [ ] Deploy em nuvem com Docker

> Este README será atualizado conforme o desenvolvimento avança. Sinta-se à vontade para acompanhar o progresso pelos commits e pela aba *Projects*.

---

## 📌 Sobre o Projeto

O **Bolão Online** é uma plataforma que permite que administradores criem bolões para diversos campeonatos (Brasileirão, Libertadores, Champions League, Copa do Mundo, entre outros), enquanto participantes se inscrevem, realizam pagamentos via Pix, fazem seus palpites e acompanham sua posição em um ranking atualizado automaticamente.

O grande diferencial do sistema é a **automação**: os jogos, resultados e classificações são importados diretamente de uma API de futebol, eliminando a necessidade de atualização manual pelo administrador.

---

## ✨ Funcionalidades

### 👤 Participante
- Visualização dos campeonatos e bolões disponíveis
- Inscrição com envio de comprovante de pagamento (Pix)
- Liberação de acesso após aprovação do administrador
- Envio de palpites antes do início das partidas
- Acompanhamento de pontuação e classificação em tempo real
- Visualização do ranking geral e premiação

### 🛠️ Administrador
- Dashboard com indicadores gerais do sistema
- Aprovação/rejeição de participantes e pagamentos
- Gerenciamento de campeonatos, regras de pontuação e premiações
- **Dashboard financeiro**: arrecadação por campeonato, fase, período, pagamentos pendentes/confirmados
- Acompanhamento de todas as apostas e rankings

---

## 🏗️ Arquitetura & Fluxo

```
Participante → Escolhe campeonato → Paga via Pix → Envia comprovante
      → Admin aprova → Acesso liberado → Participante faz palpites
      → API de Futebol atualiza resultados → Pontuação calculada automaticamente
      → Ranking atualizado em tempo real
```

**Integração externa:** a plataforma consome uma API de futebol para obter campeonatos, temporadas, times, jogos, datas, resultados oficiais e classificações.

---

## 🧰 Tecnologias

| Camada          | Tecnologias                                        | Status              |
|-----------------|-----------------------------------------------------|----------------------|
| Back-end        | Java, Spring Boot, Spring Security, JPA/Hibernate    | 🚧 Em desenvolvimento |
| Banco de Dados  | PostgreSQL                                           | 🚧 Em desenvolvimento |
| Front-end       | Angular                                              | ⏳ Não iniciado       |
| Infraestrutura  | Docker, Hospedagem em nuvem                          | ⏳ Não iniciado       |

---

## 🚀 Como Executar (em construção)

```bash
# Clonar o repositório
git clone https://github.com/juniorsousa53339-svg/bolao-online.git

# Back-end
cd backend
./mvnw spring-boot:run
```

> ⚠️ O front-end ainda não foi desenvolvido. Instruções detalhadas de configuração (variáveis de ambiente, banco de dados, chave da API de futebol) serão adicionadas conforme o projeto avança.

---

## 🌿 Fluxo de Trabalho (GitFlow)

Este projeto segue o modelo **GitFlow** para organização de branches e versionamento, garantindo um desenvolvimento mais estruturado e seguro.

| Branch        | Finalidade                                                   |
|---------------|-----------------------------------------------------------------|
| `main`        | Código estável, pronto para produção                          |
| `develop`     | Branch principal de desenvolvimento, integra as features       |
| `feature/*`   | Novas funcionalidades (ex: `feature/cadastro-participante`)     |
| `release/*`   | Preparação de uma nova versão antes do deploy                  |
| `hotfix/*`    | Correções urgentes aplicadas diretamente na `main`             |

### Exemplo de fluxo utilizado:

```bash
# Criando uma nova feature a partir da develop
git checkout develop
git checkout -b feature/nome-da-funcionalidade

# Após finalizar, merge de volta na develop
git checkout develop
git merge feature/nome-da-funcionalidade

# Quando uma versão estiver pronta para produção
git checkout -b release/1.0.0 develop
# ...testes e ajustes finais...
git checkout main
git merge release/1.0.0
git tag -a v1.0.0 -m "Primeira versão estável"
```

> Essa organização facilita o versionamento, o trabalho em paralelo entre back-end e front-end, e mantém a `main` sempre estável.

---

## 🗺️ Roadmap

1. Finalizar modelagem e endpoints do back-end *(em andamento)*
2. Implementar autenticação e perfis (admin/participante)
3. Integrar API externa de futebol
4. Desenvolver front-end em Angular
5. Implementar dashboard financeiro
6. Testes e deploy com Docker

---

## 👨‍💻 Autor

Desenvolvido por **Luciano Junior**
[LinkedIn](https://www.linkedin.com/in/lucianosousa001/) • [GitHub](https://github.com/juniorsousa53339-svg)

---

## 📄 Licença

Este projeto está sob a licença MIT — veja o arquivo [LICENSE](LICENSE) para mais detalhes.
