# Referência Técnica — Integração com API-Football (API-Sports)

Este documento estabelece a especificação técnica para a futura integração da **Palpitou API** com a **API-Football (API-Sports)**, cobrindo autenticação, endpoints, mapeamento de dados e conexão com a arquitetura interna do backend.

---

## 1. Visão Geral da API
A **API-Football** fornece dados abrangentes de futebol em tempo real, cobrindo mais de 1.000 ligas e copas ao redor do mundo. Ela atende perfeitamente ao escopo inicial do Palpitou (Campeonato Brasileiro e Campeonato Paulista) e permite expansão futura para campeonatos internacionais.

- **Site Oficial:** [https://www.api-football.com/](https://www.api-football.com/)
- **Formato:** REST / JSON
- **Documentação:** [https://www.api-football.com/documentation-v3](https://www.api-football.com/documentation-v3)

---

## 2. Processo de Autenticação
A autenticação é realizada através de um cabeçalho HTTP personalizado (`x-apisports-key`) contendo a chave de API (token) obtida no painel do usuário após o cadastro.

Exemplo de cabeçalho:
```http
x-apisports-key: SUA_CHAVE_DE_API_AQUI
```

---

## 3. Limites de Requisições e Planos (2026)

- **Plano Gratuito (Free):**
  - **Limite:** 100 requisições por dia (reseta diariamente às 00:00 UTC).
  - **Custo:** $0
  - **Uso ideal:** Testes, desenvolvimento local e homologação inicial.
- **Plano PRO:**
  - **Limite:** 7.500 requisições por dia | Limite de taxa: 300 requisições/min.
  - **Custo:** A partir de US$ 19 mensais.
  - **Uso ideal:** Produção para bases de usuários em crescimento.
- **Planos Superiores (Ultra / Mega):**
  - Escaláveis de 75.000 a 150.000+ requisições/diárias.

---

## 4. Endpoints Principais para o Palpitou

### A. Campeonatos e Ligas (`/leagues`)
* **Objetivo:** Consultar ligas disponíveis, temporadas e países.
* **Exemplo de Requisição:** `GET https://v3.football.api-sports.io/leagues?country=Brazil&season=2026`
* **Dados Relevantes:** ID da liga, nome, temporada, logotipo.

### B. Times (`/teams`)
* **Objetivo:** Obter dados dos clubes participantes de uma liga.
* **Exemplo de Requisição:** `GET https://v3.football.api-sports.io/teams?league=71&season=2026` (Ex: Brasileirão Série A)
* **Dados Relevantes:** ID do time, nome, código (sigla), escudo.

### C. Partidas / Calendário (`/fixtures`)
* **Objetivo:** Consultar jogos por rodada, data ou ID, incluindo placares e status.
* **Exemplo de Requisição:** `GET https://v3.football.api-sports.io/fixtures?league=71&season=2026&round=1`
* **Dados Relevantes:**
  - ID do jogo (`fixture.id`)
  - Data e hora (`fixture.date`)
  - Status (`fixture.status.short`: NS = Not Started, 1H = First Half, FT = Match Finished)
  - Times mandante e visitante (`teams.home`, `teams.away`)
  - Gols (`goals.home`, `goals.away`)

---

## 5. Exemplo de Resposta JSON (Endpoint `/fixtures`)

```json
{
  "get": "fixtures",
  "parameters": {
    "league": "71",
    "season": "2026",
    "round": "Regular Season - 1"
  },
  "errors": [],
  "results": 1,
  "response": [
    {
      "fixture": {
        "id": 102345,
        "referee": "João Silva",
        "timezone": "UTC",
        "date": "2026-04-10T20:00:00+00:00",
        "timestamp": 1775937600,
        "status": {
          "long": "Match Finished",
          "short": "FT",
          "elapsed": 90
        }
      },
      "league": {
        "id": 71,
        "name": "Campeonato Brasileiro Série A",
        "country": "Brazil",
        "season": 2026,
        "round": "Regular Season - 1"
      },
      "teams": {
        "home": {
          "id": 120,
          "name": "Palmeiras",
          "logo": "https://media.api-sports.io/football/teams/120.png",
          "winner": true
        },
        "away": {
          "id": 121,
          "name": "Corinthians",
          "logo": "https://media.api-sports.io/football/teams/121.png",
          "winner": false
        }
      },
      "goals": {
        "home": 2,
        "away": 1
      },
      "score": {
        "halftime": { "home": 1, "away": 0 },
        "fulltime": { "home": 2, "away": 1 }
      }
    }
  ]
}
```

---

## 6. Tratamento de Erros e Limites
- **Rate Limit Exceeded (HTTP 429):** Ocorre quando o limite por minuto (300 req/min) ou o limite diário (100 req/dia no plano free) é atingido. O cliente HTTP deve tratar a exceção logando o erro e aguardando o próximo ciclo.
- **Chave Inválida (HTTP 403):** Retornado caso o cabeçalho `x-apisports-key` esteja ausente ou incorreto.

---

## 7. Conexão com a Arquitetura do Palpitou (`integration` package)

Para manter a separação de responsabilidades e a arquitetura limpa:

1. **Camada de Cliente HTTP (`integration.client`):**
   - Utilizará o `RestClient` do Spring Boot para disparar as chamadas HTTP para a API-Football injetando a chave de segurança.
2. **DTOs de Integração (`integration.dto`):**
   - Classes record para desserializar os JSONs retornados pela API externa de forma isolada das entidades de domínio.
3. **Serviço de Sincronização (`integration.service` ou `ExternalSyncService`):**
   - Um serviço que consome os clientes da API externa, converte os DTOs externos para as entidades internas (`Campeonato`, `Time`, `Jogo`) utilizando mappers e persiste via `Repository`.
4. **Integração com Regras de Negócio:**
   - Quando um job de sincronização atualizar um jogo para o status `FT` (Finished) com os gols oficiais, o sistema acionará o `PontuacaoService` para recalcular os palpites e atualizar o ranking dos usuários automaticamente.
