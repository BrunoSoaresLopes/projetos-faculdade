## Aluguel de Imóveis

Este é um projeto Java com integração ao banco de dados MySQL que simula um sistema de cadastro e gestão de imóveis para aluguel.

## Funcionalidades

- Cadastro de novos imóveis para alugar
- Edição de imóveis cadastrados
- Visualização de todos os imóveis disponíveis no sistema
- Armazenamento das informações em banco de dados MySQL
- Associação de cada imóvel a um usuário (proprietário)

## Tecnologias Utilizadas

- Java
- MySQL
- JDBC (para conexão com o banco)
- Eclipse IDE

## Configuração do Banco de Dados

CREATE DATABASE aluguel_imoveis;
USE aluguel_imoveis;

CREATE TABLE usuario (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE imoveis (
    id INT IDENTITY(1,1) PRIMARY KEY,
    endereco VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    valor DECIMAL(12, 2) NOT NULL,
    informacoes VARCHAR(500),
    cpf_usuario VARCHAR(11) NOT NULL,
    FOREIGN KEY (cpf_usuario) REFERENCES usuario(cpf)
);

## Autor
Desenvolvido por Bruno Soares Lopes.
Contato: [brunosoareslopes13@gmail.com]


