# 🚀 **E-Commerce**

> Este projeto é uma API para gerenciamento de um e-commerce, construída com **Java** e **Spring Boot**.

---

## 📝 **Descrição**
Este projeto tem como objetivo fornecer uma API RESTful para gerenciar clientes, produtos e compras incluindo funcionalidades como:
- 📄 Cadastro de clientes;
- 🔍 Consulta de clientes por CPF.
- 🔄 Atualização de informações;
- 📄 Cadastro de produtos;
- 🔍 Consulta de todos produtos.
- ❌ Exclusão de produtos;
- 🛒 Realizar compras.

---

## 🛠️ **Tecnologias Utilizadas**
As principais tecnologias e ferramentas utilizadas no desenvolvimento deste projeto são:
- ☕ **Java 17**
- 🌱 **Spring Boot 3.4.2**
- 💾 **H2-database**
- 🔧 **Maven** para gerenciamento de dependências

---

## ⚙️ **Pré-requisitos**
Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:  
. 🖥️ **Java 17**    
. 🛠️ **Maven**

---

## 🚀 **Como Executar o Projeto**
Siga os passos abaixo para rodar o projeto localmente:

```mvn spring-boot:run```

Acesse a API no navegador ou via Postman:

http://localhost:8080

---

## 📚 **Endpoints Disponíveis**
Aqui estão os principais endpoints da API:

**Clientes**
- GET /clientes/{cpf} - Retorna um cliente pelo CPF
- POST /clientes - Cadastra um novo cliente
- PUT /clientes/{cpf} - Atualiza as informações de um cliente.  

**Produtos**
- GET /produtos - Retorna todos os produtos
- POST /produtos - Cadastra um novo produto
- DELETE /protutos/{id} - Remove um produto

**Compras**
- POST /compras - realiza uma compra
