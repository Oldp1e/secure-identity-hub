# Secure Identity Hub

Este projeto é um **hub de identidade e autenticação** desenvolvido em **Java 21 / Spring Boot 3**, com o objetivo de demonstrar boas práticas de **POO, APIs REST, segurança com Spring Security, OAuth2/JWT, testes automatizados e deploy com Docker**.

## Estrutura
- **auth-server** → Serviço de autenticação (gera e valida tokens JWT, gerencia credenciais e escopos de acesso).
- **resource-api** → API de exemplo protegida, que consome tokens emitidos pelo auth-server.

## Principais Tecnologias
- **Java 21**, **Spring Boot 3**, **Spring Security**
- **OAuth2 / JWT** para autenticação e autorização
- **JUnit 5** para testes automatizados
- **Maven** para build e gestão de dependências
- **Docker/Compose** para ambientes de execução
- **GitHub Actions** para CI/CD

---

> ⚠️ Observação: o projeto está em evolução, começando pelo `auth-server` com autenticação básica. A próxima etapa será implementar **emissão e validação de tokens JWT** e proteger a `resource-api` com escopos de acesso.
