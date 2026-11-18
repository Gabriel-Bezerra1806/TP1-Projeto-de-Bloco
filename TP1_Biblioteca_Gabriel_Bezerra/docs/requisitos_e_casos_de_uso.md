# TP1 — Sistema de Gestão de Biblioteca Universitária

**Aluno:** Gabriel Bezerra de Sousa  
**Disciplina:** [PREENCHER CÓDIGO DA DISCIPLINA]  
**Trilha tecnológica:** Java 21  
**Versão:** 1.0 — 17/11/2026

## 1. Objetivo e visão geral

O sistema tem como objetivo apoiar a gestão do acervo de uma biblioteca universitária e controlar o relacionamento entre livros, exemplares físicos, membros, empréstimos, reservas e multas. O sistema deverá permitir consultas pelos membros e operações administrativas realizadas por bibliotecários/administradores.

## 2. Atores

- **Membro:** aluno, professor ou funcionário. Consulta o acervo e solicita empréstimos, devoluções e reservas, conforme as regras da biblioteca.
- **Bibliotecário/Administrador:** mantém os cadastros e executa ou valida operações de empréstimo, devolução e multa.

## 3. Requisitos funcionais

| ID | Requisito | Prioridade |
|---|---|---|
| RF01 | O sistema deve permitir cadastrar livros com título, autor e ISBN. | Alta |
| RF02 | O sistema deve permitir cadastrar exemplares físicos vinculados a um livro, com código de patrimônio e estado de conservação. | Alta |
| RF03 | O sistema deve permitir cadastrar membros com nome, matrícula e tipo (aluno, professor ou funcionário). | Alta |
| RF04 | O sistema deve permitir consultar o acervo por informações do livro e visualizar a disponibilidade de exemplares. | Alta |
| RF05 | O sistema deve permitir registrar empréstimo de exemplar para um membro. | Alta |
| RF06 | Ao registrar empréstimo, o sistema deve definir prazo padrão de devolução de 14 dias. | Alta |
| RF07 | O sistema deve impedir novo empréstimo quando o membro já possuir 3 empréstimos ativos. | Alta |
| RF08 | O sistema deve impedir empréstimo de exemplar que não esteja disponível. | Alta |
| RF09 | O sistema deve permitir registrar a devolução de um exemplar emprestado. | Alta |
| RF10 | O sistema deve calcular multa por atraso com base na quantidade de dias corridos após o prazo. | Alta |
| RF11 | O sistema deve permitir reservar um livro quando não houver exemplar disponível por estar emprestado, registrando membro e data da reserva. | Alta |
| RF12 | O sistema deve impedir reserva quando o livro possuir exemplar disponível, conforme regra de referência fornecida. | Alta |
| RF13 | O sistema deve permitir ao bibliotecário/administrador consultar empréstimos ativos e devoluções pendentes. | Média |
| RF14 | O sistema deve permitir consultar reservas registradas. | Média |
| RF15 | O sistema deve manter o vínculo entre exemplar e livro, e entre empréstimo e exemplar/membro. | Alta |

## 4. Requisitos não funcionais

| ID | Requisito | Categoria |
|---|---|---|
| RNF01 | A solução deverá ser desenvolvida inicialmente em Java 21, conforme trilha escolhida para o projeto. | Tecnologia |
| RNF02 | A primeira entrega deverá executar como programa standalone, sem depender de servidor web ou banco de dados. | Arquitetura/escopo |
| RNF03 | As operações e mensagens deverão ser apresentadas em português claro, com instruções compreensíveis. | Usabilidade |
| RNF04 | O sistema deverá validar entradas e informar ao usuário quando uma operação não puder ser realizada. | Confiabilidade/usabilidade |
| RNF05 | As regras de negócio deverão ser implementadas de forma consistente, evitando empréstimos duplicados do mesmo exemplar e ultrapassagem do limite por membro. | Integridade |
| RNF06 | O código deverá ser organizado e legível, permitindo evolução incremental nas próximas entregas. | Manutenibilidade |
| RNF07 | Nas etapas futuras, a solução deverá poder evoluir para persistência, API web e autenticação, conforme orientação da disciplina. | Evolutividade |
| RNF08 | O repositório deverá manter documentação em `docs/` e código-fonte em `src/`, com identificação do aluno e da disciplina. | Entrega |

> Observação: a especificação não informa metas numéricas de desempenho, disponibilidade ou volume de usuários. Por isso, não foram inventados limites mensuráveis para esses atributos.

## 5. Regras de negócio

- **RN01 — Prazo:** cada empréstimo terá prazo padrão de 14 dias.
- **RN02 — Limite:** um membro poderá possuir no máximo 3 empréstimos ativos simultaneamente.
- **RN03 — Disponibilidade:** somente exemplares disponíveis poderão ser emprestados.
- **RN04 — Multa:** o atraso será contado em dias corridos. O valor monetário por dia ainda precisa ser definido pelo professor/biblioteca.
- **RN05 — Reserva:** a reserva será feita para um livro e somente será permitida quando seus exemplares estiverem emprestados, isto é, sem exemplar disponível no momento da solicitação.
- **RN06 — Identificação:** cada exemplar físico deverá possuir código de patrimônio próprio e estar associado a um livro.
- **RN07 — Tipos de membro:** os tipos considerados nesta versão são aluno, professor e funcionário.

## 6. Casos de uso

### UC01 — Consultar acervo

**Ator principal:** Membro  
**Objetivo:** localizar um livro e verificar se há exemplares disponíveis.  
**Pré-condição:** o sistema está disponível.  
**Pós-condição:** a consulta é apresentada; nenhum dado é alterado.

**Fluxo principal**
1. O membro solicita a consulta do acervo.
2. O sistema solicita um termo de pesquisa (título, autor ou ISBN).
3. O membro informa o termo.
4. O sistema pesquisa os livros correspondentes.
5. O sistema apresenta os dados dos livros e a situação de disponibilidade dos exemplares.

**Fluxo alternativo**
- 4a. Nenhum livro é encontrado: o sistema informa que não existem resultados e permite uma nova pesquisa.

### UC02 — Solicitar/registrar empréstimo

**Ator principal:** Membro  
**Ator de apoio:** Bibliotecário/Administrador  
**Objetivo:** registrar a retirada de um exemplar por um membro.  
**Pré-condições:** membro e exemplar cadastrados; exemplar disponível.  
**Pós-condição:** empréstimo ativo registrado com prazo de 14 dias.

**Fluxo principal**
1. O membro solicita o empréstimo de um exemplar.
2. O sistema identifica o membro e o exemplar.
3. O sistema verifica se o membro possui menos de 3 empréstimos ativos.
4. O sistema verifica se o exemplar está disponível.
5. O bibliotecário confirma/processa a operação (quando aplicável ao procedimento da biblioteca).
6. O sistema registra a data de retirada e calcula o prazo de devolução de 14 dias.
7. O sistema confirma o empréstimo.

**Fluxos alternativos**
- 3a. O membro já possui 3 empréstimos ativos: o sistema bloqueia a operação e informa o motivo.
- 4a. O exemplar não está disponível: o sistema não registra o empréstimo e informa a indisponibilidade.
- 2a. O membro ou exemplar não está cadastrado: o sistema informa que o cadastro não foi localizado.

### UC03 — Registrar devolução e calcular multa

**Ator principal:** Bibliotecário/Administrador  
**Ator de apoio:** Membro  
**Objetivo:** registrar a devolução de exemplar e apurar eventual atraso.  
**Pré-condição:** existe empréstimo ativo associado ao exemplar.  
**Pós-condição:** empréstimo encerrado; eventual multa calculada e apresentada.

**Fluxo principal**
1. O membro entrega o exemplar.
2. O bibliotecário informa/identifica o exemplar ou empréstimo.
3. O sistema localiza o empréstimo ativo.
4. O sistema registra a data de devolução.
5. O sistema compara a data de devolução com o prazo.
6. Se houver atraso, o sistema calcula os dias corridos de atraso e a multa conforme a tarifa diária configurada.
7. O sistema encerra o empréstimo e confirma a devolução.

**Fluxos alternativos**
- 3a. Não existe empréstimo ativo para o exemplar: o sistema informa que não há devolução pendente e não altera o registro.
- 5a. Devolução dentro do prazo: o sistema não gera multa e encerra o empréstimo normalmente.
- 6a. Tarifa diária não configurada: o sistema informa que o valor monetário depende de configuração administrativa; não deve inventar uma tarifa.

### UC04 — Reservar livro

**Ator principal:** Membro  
**Ator de apoio:** Bibliotecário/Administrador (quando a política exigir validação)  
**Objetivo:** registrar interesse em um livro sem exemplares disponíveis.  
**Pré-condição:** membro e livro cadastrados; todos os exemplares do livro estão emprestados.  
**Pós-condição:** reserva registrada com membro, livro e data.

**Fluxo principal**
1. O membro seleciona um livro.
2. O sistema verifica a disponibilidade dos exemplares vinculados.
3. O sistema constata que todos estão emprestados.
4. O sistema registra a reserva com membro, livro e data.
5. O sistema confirma a reserva.

**Fluxos alternativos**
- 2a. Existe ao menos um exemplar disponível: o sistema não permite a reserva e informa que o livro pode ser solicitado para empréstimo.
- 1a. Livro ou membro não cadastrado: o sistema informa que não foi possível localizar os dados.

## 7. Justificativa de escopo do TP1

### Dentro do escopo
- Levantamento e classificação dos requisitos funcionais e não funcionais.
- Identificação dos atores e modelagem dos casos de uso principais.
- Definição explícita das regras de negócio conhecidas.
- Configuração do ambiente Java 21 e IntelliJ IDEA Community Edition.
- Programa standalone introdutório, com tipos primitivos, operadores, condicionais e repetição, sem classes de domínio, banco de dados ou interface web.

### Fora do escopo nesta entrega
- Implementação completa de cadastro de livros, exemplares e membros.
- Persistência em banco de dados.
- API REST, interface web, autenticação e autorização.
- Fila/prioridade automática de reservas e notificações.
- Pagamento, negociação ou baixa financeira de multas.
- Relatórios gerenciais e integração com sistemas acadêmicos.

Esses itens ficam para etapas posteriores, após validação do modelo e do escopo com o professor.

## 8. Suposições e pontos a validar com o professor

1. **Tarifa da multa:** foi informada a cobrança por dia corrido, mas não o valor diário. O valor deverá ser parametrizado ou definido pelo professor.
2. **Reserva por livro:** a entidade Reserva referencia Livro, não Exemplar. Assim, a reserva é interpretada como interesse no título, e não em uma unidade física específica.
3. **Disponibilidade para reserva:** considera-se que basta existir um exemplar disponível para bloquear a reserva daquele livro.
4. **Empréstimos ativos:** entende-se como ativo o empréstimo retirado e ainda não devolvido.
5. **Data de vencimento:** considera-se prazo de 14 dias corridos a partir da data de retirada. A regra para horário, feriados e vencimento em dia não útil não foi especificada.
6. **Multa:** considera-se atraso a diferença positiva, em dias corridos, entre a data efetiva de devolução e a data-limite. A política de tolerância não foi informada.
7. **Responsabilidade operacional:** a descrição atribui solicitações ao membro e processamento ao bibliotecário/administrador. O fluxo de empréstimo foi modelado com solicitação do membro e processamento/validação administrativa.
8. **Cancelamento e expiração de reserva:** não foram especificados e ficam fora desta entrega.
9. **Bloqueios por multas em aberto:** não foram especificados e não serão presumidos.
10. **Autonomia do membro na devolução:** a devolução é registrada pelo bibliotecário/administrador, conforme os papéis descritos.

## 9. Protótipo standalone

O programa inicial é uma demonstração de lógica básica em Java. Ele simula disponibilidade, limite de empréstimos e cálculo ilustrativo de multa. Não representa ainda o sistema completo e não armazena dados após o encerramento.

**Importante:** a tarifa de multa usada no protótipo é apenas um valor de demonstração (R$ 2,00/dia), não uma regra oficial. Deve ser confirmada ou substituída após orientação do professor.

## 10. Checklist de validação antes da entrega

- [ ] Confirmar com o professor o valor diário da multa e as suposições listadas.
- [ ] Substituir o código da disciplina em todos os arquivos.
- [ ] Instalar JDK 21 e IntelliJ IDEA Community Edition.
- [ ] Executar `Main.java` no ambiente local e registrar evidência.
- [ ] Criar repositório GitHub e enviar `docs/` e `src/`.
- [ ] Gravar vídeo de até 5 minutos demonstrando o programa e explicando as decisões.
- [ ] Conferir se PDF, ZIP e repositório possuem a identificação correta.
