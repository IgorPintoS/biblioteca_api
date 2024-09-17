# 📚 BookController - API para Gerenciamento de Livros
Este repositório contém a implementação de um **Controller** que gerencia as operações de livros em uma API RESTful. O **BookController** é responsável por lidar com as requisições HTTP e mapear as operações CRUD (Create, Read, Update, Delete) para o gerenciamento de livros.

# 🧪 Testando a API
Você pode testar os endpoints utilizando ferramentas como o Postman, Insomnia ou através do cURL no terminal.

# 🛠️ Tecnologias Utilizadas
Java 11: Linguagem de programação utilizada.
Spring Boot: Framework para desenvolvimento rápido de aplicações.
UUID: Gerenciamento de identificadores únicos para cada livro.

```mermaid
classDiagram
    class Book {
        - UUID idBook
        - String name
        - String genre
        - String author
        - String publisher
    }
