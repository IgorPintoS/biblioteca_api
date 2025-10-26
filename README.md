# 📚 API de Biblioteca (biblioteca_api)

![Java](https://img.shields.io/badge/Java-21-blue?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.4-darkgreen?style=for-the-badge&logo=spring&logoColor=white)
![JPA](https://img.shields.io/badge/Spring_Data_JPA-Gray?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-red?style=for-the-badge&logo=apache-maven&logoColor=white)

## 🎯 Sobre o Projeto

**biblioteca_api** é uma API RESTful robusta desenvolvida em Java com Spring Boot, projetada para o gerenciamento completo de uma coleção de livros. A aplicação segue as melhores práticas de desenvolvimento, como o padrão DTO (Data Transfer Object), validação de dados e uma arquitetura em camadas (Controller, Model, Repository, Mapper).

Este projeto demonstra habilidades em construção de APIs, gerenciamento de banco de dados com JPA/Hibernate e estruturação de código limpo e manutenível.

## ✨ Principais Características

- **API RESTful Completa:** Implementação de todas as operações CRUD (Create, Read, Update, Delete) para o gerenciamento de livros.
- **Padrão DTO (Data Transfer Object):** Utilização de DTOs (`BookCreateDTO`, `BookUpdateDTO`, `BookResponseDTO`) para desacoplar a entidade do modelo de dados da API, garantindo maior segurança e flexibilidade.
- **Java Records:** Uso de Records do Java para a criação de DTOs imutáveis e concisos.
- **Padrão Mapper:** Implementação de um `BookMapper` (`@Component`) para conversão limpa e eficiente entre Entidades (`Book`) e DTOs.
- **Validação de Dados:** Uso do Spring Validation (`@Valid`) para garantir a integridade dos dados recebidos nos endpoints.
- **Spring Data JPA:** Abstração de persistência de dados e consultas utilizando `JpaRepository`.
- **Injeção de Dependência:** Aplicação do princípio de Inversão de Controle (IoC) do Spring, visível na injeção do `BookRepository` e `BookMapper` via construtor no `BookController`.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.1.4
- **Módulos Spring:**
  - Spring Web (para APIs REST)
  - Spring Data JPA (para persistência de dados)
  - Spring Validation (para validação de DTOs)
- **Banco de Dados:** PostgreSQL
- **Gerenciador de Build:** Apache Maven
- **Utilitários:** Lombok

## 🗺️ Endpoints da API

Abaixo está a documentação dos endpoints disponíveis para a entidade `Book`.

| Verbo HTTP | Endpoint      | Descrição                                     | Payload (Corpo) | Resposta (Sucesso)                    |
| :--------- | :------------ | :-------------------------------------------- | :-------------- | :------------------------------------ |
| `POST`     | `/books`      | Cria um novo livro.                           | `BookCreateDTO` | `201 CREATED` + `BookResponseDTO`     |
| `GET`      | `/books`      | Lista todos os livros cadastrados.            | N/A             | `200 OK` + Lista de `BookResponseDTO` |
| `GET`      | `/books/{id}` | Busca um livro específico pelo seu ID (UUID). | N/A             | `200 OK` + `BookResponseDTO`          |
| `PUT`      | `/books/{id}` | Atualiza os dados de um livro existente.      | `BookUpdateDTO` | `200 OK` + `BookResponseDTO`          |
| `DELETE`   | `/books/{id}` | Remove um livro do banco de dados.            | N/A             | `204 NO CONTENT`                      |

### Modelo da Entidade `Book`

A entidade `Book` (`@Entity`) que serve de base para a API possui a seguinte estrutura:

```java
@Entity
@Table(name = "LIVROS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String genre;
    private String author;
    private String publisher;
}
```
