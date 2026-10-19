# Regras de Testes — Palpitou API

- **Testes Obrigatórios:** Toda nova funcionalidade ou regra de negócio deve ser acompanhada de testes automatizados relevantes utilizando JUnit 5 e ferramentas Spring Boot Test.
- **Cenários Cobertos:** Garanta testes para caminhos de sucesso, cenários de erro, validações de entrada e tratamento de exceções de negócio.
- **Execução Real:** Sempre execute os testes pertinentes e relate os resultados de forma honesta e transparente.
- **Proibido Falsificar Evidências:** Nunca afirme que um teste passou se ele não foi executado ou se falhou. Nunca remova ou enfraqueca testes existentes para mascarar falhas no código.
- **Investigação de Falhas:** Caso um teste falhe, analise a causa raiz com o desenvolvedor em vez de alterar o teste precipitadamente para encobrir o problema.
- **Isolação:** Mantenha os testes limpos, independentes e focados em validar o comportamento esperado da unidade ou componente testado.
