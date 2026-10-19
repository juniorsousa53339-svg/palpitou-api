# Regras de Code Review — Palpitou API

- **Critérios de Avaliação:**
  - **Corretude:** A lógica atende aos requisitos propostos sem bugs aparentes?
  - **Segurança:** Existem vulnerabilidades (ex: injeção de SQL, exposição de dados sensíveis, falta de validação ou autenticação)?
  - **Integridade de Dados:** As transações, relacionamentos JPA e restrições de banco são respeitadas?
  - **Tratamento de Erros:** As exceções são capturadas e tratadas consistentemente (`GlobalExceptionHandler`, `BusinessRuleException`)?
  - **Manutenibilidade:** O código é limpo, legível, segue padrões SOLID e respeita as convenções do projeto?
  - **Cobertura de Testes:** As alterações possuem testes automatizados adequados?
- **Priorização:** Classifique os problemas encontrados por gravidade (Crítico, Alto, Médio, Baixo / Sugestão).
- **Precisão:** Indique o arquivo e o número da linha correspondente (`file_path:line_number`) sempre que possível.
- **Distinção Clara:** Separe defeitos reais (bugs/falhas) de riscos potenciais e sugestões estilísticas.
- **Foco Educacional:** Explique o motivo de cada apontamento para apoiar o aprendizado técnico do desenvolvedor.
- **Moderação:** Não altere código durante a revisão sem autorização e solicitação explícita.
