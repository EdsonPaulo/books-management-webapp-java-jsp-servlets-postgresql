DROP DATABASE IF EXISTS biblioteca_ucan;
CREATE DATABASE biblioteca_ucan;
USE biblioteca_ucan;

CREATE TABLE pais (
    pk_pais SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE provincia (
    pk_provincia SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    fk_pais INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_pais) REFERENCES pais(pk_pais)  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE municipio (
    pk_municipio SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    fk_provincia INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_provincia) REFERENCES provincia(pk_provincia) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE comuna (
    pk_comuna SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    fk_municipio INTEGER NOT NULL, 
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_municipio) REFERENCES municipio(pk_municipio) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE bairro (
    pk_bairro SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    fk_comuna INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_comuna) REFERENCES comuna(pk_comuna) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE morada (
    pk_morada SERIAL NOT NULL PRIMARY KEY,
    rua VARCHAR(10) NOT NULL,
    num_casa INTEGER,
    fk_bairro INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_bairro) REFERENCES bairro(pk_bairro) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE sexo (
    pk_sexo SERIAL NOT NULL PRIMARY KEY,
    nome CHAR(1) NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE pessoa (
    pk_pessoa SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    sobrenome VARCHAR(20) NOT NULL,
    telefone VARCHAR(14) NOT NULL,
    data_nasc DATE,
    email VARCHAR(30),
    fk_morada INTEGER NOT NULL,
    fk_sexo INTEGER,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_morada) REFERENCES morada(pk_morada) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_sexo) REFERENCES sexo(pk_sexo) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE leitor(
    pk_leitor SERIAL NOT NULL PRIMARY KEY,
    fk_pessoa INTEGER NOT NULL,
    data_criacao DATE,
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(pk_pessoa) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE autor(
    pk_autor SERIAL NOT NULL PRIMARY KEY,
    fk_pessoa INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(pk_pessoa) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE editora(
    pk_editora SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    telefone VARCHAR(14) NOT NULL,
    email VARCHAR(30) NOT NULL,
    fax VARCHAR(30),
    fk_morada INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_morada) REFERENCES morada(pk_morada) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE localizacao(
    pk_localizacao SERIAL NOT NULL PRIMARY KEY,
    num_corredor INTEGER NOT NULL,
    num_armario INTEGER NOT NULL,
    num_prateleira INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE classificacao (
    pk_classificacao SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE estado (
    pk_estado SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE descritores (
    pk_descritores SERIAL NOT NULL PRIMARY KEY,
    descricao VARCHAR(20),
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE categoria (
    pk_categoria SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(10),
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE livro (
    pk_livro SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    isbn INTEGER NOT NULL UNIQUE,
    qtd_paginas INTEGER,
    qtd_copias INTEGER,
    num_edicao INTEGER,
    fk_estado INTEGER,
    fk_classificacao INTEGER,
    fk_localizacao INTEGER NOT NULL,
    fk_categoria INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY( fk_estado ) REFERENCES estado(pk_estado) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_classificacao) REFERENCES classificacao(pk_classificacao) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_localizacao) REFERENCES localizacao(pk_localizacao) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_categoria) REFERENCES categoria(pk_categoria) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE requisicao(
    pk_requisicao SERIAL NOT NULL PRIMARY KEY,
    fk_livro INTEGER NOT NULL,
    fk_leitor INTEGER NOT NULL,
    data_requisicao TIMESTAMP NOT NULL,
    data_entrega TIMESTAMP NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_livro) REFERENCES livro(pk_livro) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_leitor) REFERENCES leitor(pk_leitor) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE livro_autor(
    pk_livro_autor SERIAL NOT NULL PRIMARY KEY,
    fk_livro INTEGER NOT NULL,
    fk_autor INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_livro) REFERENCES livro(pk_livro) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE livro_editora(
    pk_livro_editora SERIAL NOT NULL PRIMARY KEY,
    fk_livro INTEGER NOT NULL,
    fk_editora INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_livro) REFERENCES livro(pk_livro) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_editora) REFERENCES editora(pk_editora) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE livro_descritores(
    pk_livro_descritores SERIAL NOT NULL PRIMARY KEY,
    fk_livro INTEGER NOT NULL,
    fk_descritores INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_livro) REFERENCES livro(pk_livro) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_descritores) REFERENCES descritores(pk_descritores) ON UPDATE CASCADE ON DELETE CASCADE
);