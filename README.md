# SafeGuard — Sistema de Monitoramento para Proteção de Vítimas de Violência Doméstica

## Status do Projeto
🚧 Em desenvolvimento

## Tecnologias Utilizadas
- Java (Swing — interface desktop)
- MySQL 8.x (banco de dados relacional)
- JDBC (integração Java + MySQL)
- NetBeans IDE

## Time de Desenvolvedores
| Nome | Função |
|---|---|
| Marcelo Santos da Vitória | Desenvolvedor backend / banco de dados |
| Wesley da Silva Marques | Desenvolvedor frontend / interfaces |

## Objetivo do Software
O SafeGuard é um sistema desktop desenvolvido para apoiar e monitorar casos de violência doméstica. Permite o cadastro de vítimas e agressores, o registro de ocorrências e a emissão de alertas para autoridades competentes, contribuindo para a proteção e organização das informações relacionadas a medidas protetivas.

## Funcionalidades do Sistema
- ✅ Autenticação de usuários por perfil (Administrador, Autoridade, Operador)
- ✅ Cadastro e consulta de vítimas com medida protetiva
- ✅ Cadastro e consulta de agressores vinculados a vítimas
- ✅ Registro de ocorrências com tipo, local, data e descrição
- ✅ Emissão de alertas (Emergência, Aviso, Monitoramento)
- ✅ Painel principal com estatísticas e ocorrências recentes
- ✅ Integração com banco de dados MySQL via JDBC

## Como Executar
1. Importe o script `safeguard_db.sql` no MySQL Workbench
2. Adicione o driver `mysql-connector-java.jar` ao projeto no NetBeans
3. Ajuste usuário/senha em `safeguard/db/ConexaoBD.java` se necessário
4. Execute a classe `safeguard.ui.Main`

**Credenciais de teste:** `admin@safeguard.br` / `1234`

## Estrutura de Pacotes
```
safeguard/
├── db/           # Acesso ao banco (DAOs + ConexaoBD)
├── ui/           # Interfaces gráficas (Swing)
│   ├── Estilo.java
│   ├── Main.java
│   ├── TelaLogin.java
│   ├── TelaPrincipal.java
│   ├── PainelInicio.java
│   ├── PainelVitima.java
│   ├── PainelAgressor.java
│   ├── PainelOcorrencia.java
│   └── PainelAlerta.java
└── (model classes)
```
