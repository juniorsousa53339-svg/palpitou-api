# Palpitou API — Instruções do Projeto

## 1. Objetivo

O Palpitou é uma aplicação backend para bolões de futebol,
com foco inicial no Campeonato Brasileiro e no Campeonato Paulista.

O sistema permite gerenciar bolões, campeonatos, times, jogos,
participações, pagamentos, palpites, premiações e pontuações.

O objetivo é construir uma API REST organizada, segura, testável
e preparada para integração com um frontend e serviços externos.

## 2. Stack tecnológica

- Java 17
- Spring Boot 4.1.0
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- Lombok
- H2 Database
- SpringDoc OpenAPI / Swagger
- Maven

Consulte o código e o arquivo pom.xml para confirmar a configuração atual.

## 3. Fonte de verdade

Antes de executar qualquer tarefa:

1. Leia este arquivo.
2. Consulte a documentação de contexto do projeto, quando disponível.
3. Inspecione os arquivos relevantes do código.
4. Não presuma que a documentação está mais atualizada que o código.
5. Em caso de divergência, apresente a diferença antes de propor alterações.

## 4. Modo de trabalho

O desenvolvedor está aprendendo e deve continuar responsável
pela implementação e pelas decisões técnicas.

- Explique conceitos novos antes de utilizá-los.
- Prefira tarefas pequenas e incrementais.
- Explique o motivo das decisões de arquitetura.
- Não entregue grandes blocos de código sem necessidade.
- Priorize compreensão, qualidade e autonomia do desenvolvedor.
- Não implemente funcionalidades fora do escopo solicitado.
- Não altere arquivos sem relação com a tarefa.
- Não invente requisitos nem regras de negócio.

## 5. Qualidade do código

Priorize:

- Separação de responsabilidades.
- Código simples e legível.
- Regras de negócio na camada apropriada.
- Validação de entradas e tratamento consistente de erros.
- Segurança e integridade dos dados.
- Testes automatizados relevantes.
- Reutilização sem abstrações desnecessárias.

Respeite os padrões já utilizados no projeto, salvo quando houver
uma justificativa clara para alterá-los.

## 6. Proteção do repositório

Antes de modificar arquivos, verifique o estado do Git.

- Preserve alterações locais não commitadas.
- Nunca descarte alterações do desenvolvedor sem autorização.
- Não execute comandos destrutivos sem explicar o risco e obter autorização.
- Não faça commits, pushes ou merges sem solicitação explícita.
- Nunca exponha senhas, tokens ou chaves de API.

## 7. Fluxo de execução

Para cada tarefa:

1. Entenda o objetivo e o escopo.
2. Inspecione o código relacionado.
3. Explique a abordagem proposta.
4. Divida o trabalho em etapas pequenas.
5. Implemente apenas o que foi combinado.
6. Execute os testes pertinentes.
7. Resuma as alterações, os resultados e as pendências.

## 8. Segurança

A configuração de segurança atual precisa ser revisada antes de
qualquer disponibilização pública.

Não considere a aplicação pronta para produção enquanto existirem
pendências relevantes de autenticação, autorização, persistência,
validação ou configuração de ambiente.

## 9. Agentes

Os agentes especializados devem respeitar estas instruções e trabalhar
com o mesmo contexto do projeto.

Nenhum agente deve modificar arquivos fora de sua responsabilidade
ou executar tarefas sem relação com o objetivo solicitado.