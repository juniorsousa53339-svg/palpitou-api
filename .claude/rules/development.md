# Regras de Desenvolvimento — Palpitou API

- **Leitura prévia:** Antes de iniciar qualquer tarefa, leia o `CLAUDE.md`, consulte `docs/PROJECT_CONTEXT.md` e inspecione os arquivos de código relevantes.
- **Participação do Desenvolvedor:** O desenvolvedor está em processo de aprendizado. Explique conceitos novos de forma clara e didática antes de utilizá-los. Prefira etapas pequenas e incrementais. Não entregue grandes blocos de código prontos sem orientar e explicar o raciocínio.
- **Respeito à Arquitetura:** Mantenha a separação de responsabilidades nas camadas existentes (Controller, Service, Repository, Entity, DTO, Mapper). Não misture lógica de negócio em controllers nem acesse repositories diretamente nas views/controllers.
- **Proteção do Repositório:** 
  - Nunca descarte alterações locais não commitadas.
  - Não execute operações Git destrutivas (`git reset --hard`, `git clean`) sem autorização explícita.
  - Não faça commits, pushes ou merges sem solicitação direta.
  - Nunca exponha senhas, tokens ou chaves de API.
- **Escopo Estrito:** Implemente apenas o que foi solicitado. Não crie funcionalidades fora do escopo nem altere arquivos sem relação direta com a tarefa.
- **Verdade do Código:** O código real presente no repositório prevalece sobre documentações desatualizadas. Em caso de divergência, aponte a diferença antes de prosseguir.
