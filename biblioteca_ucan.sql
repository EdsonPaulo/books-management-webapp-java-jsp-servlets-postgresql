DROP TABLE IF EXISTS pais CASCADE;
CREATE TABLE pais (
    pk_pais SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS provincia CASCADE;
CREATE TABLE provincia (
    pk_provincia SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    fk_pais INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_pais) REFERENCES pais(pk_pais)  ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS municipio CASCADE;
CREATE TABLE municipio (
    pk_municipio SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    fk_provincia INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_provincia) REFERENCES provincia(pk_provincia) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS comuna CASCADE;
CREATE TABLE comuna (
    pk_comuna SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    fk_municipio INTEGER NOT NULL, 
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_municipio) REFERENCES municipio(pk_municipio) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS morada CASCADE;
CREATE TABLE morada (
    pk_morada SERIAL NOT NULL PRIMARY KEY,
    rua VARCHAR(50) NOT NULL,
    num_casa INTEGER,
    bairro VARCHAR(50) NOT NULL,
    fk_comuna INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_comuna) REFERENCES comuna(pk_comuna) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS sexo CASCADE;
CREATE TABLE sexo (
    pk_sexo SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(10) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS pessoa CASCADE;
CREATE TABLE pessoa (
    pk_pessoa SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    bi VARCHAR(50) NOT NULL,
    data_nasc DATE,
    fk_morada INTEGER NOT NULL,
    fk_sexo INTEGER,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_morada) REFERENCES morada(pk_morada) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_sexo) REFERENCES sexo(pk_sexo) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS telefone_pessoa CASCADE;
CREATE TABLE telefone_pessoa (
    pk_telefone_pessoa SERIAL NOT NULL PRIMARY KEY,
    numero VARCHAR(14) UNIQUE NOT NULL,
    fk_pessoa INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(pk_pessoa) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS email_pessoa CASCADE;
CREATE TABLE email_pessoa (
    pk_email_pessoa SERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    fk_pessoa INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(pk_pessoa) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS leitor CASCADE;
CREATE TABLE leitor (
    pk_leitor SERIAL NOT NULL PRIMARY KEY,
    fk_pessoa INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(pk_pessoa) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS autor CASCADE;
CREATE TABLE autor (
    pk_autor SERIAL NOT NULL PRIMARY KEY,
    fk_pessoa INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(pk_pessoa) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS editora CASCADE;
CREATE TABLE editora (
    pk_editora SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,    
    nif VARCHAR(14) NOT NULL,
    fax VARCHAR(50),
    fk_morada INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_morada) REFERENCES morada(pk_morada) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS telefone_editora CASCADE;
CREATE TABLE telefone_editora (
    pk_telefone_editora SERIAL NOT NULL PRIMARY KEY,
    numero VARCHAR(14) UNIQUE NOT NULL,
    fk_editora INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_editora) REFERENCES editora(pk_editora) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS email_editora CASCADE;
CREATE TABLE email_editora (
    pk_email_editora SERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    fk_editora INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_editora) REFERENCES editora(pk_editora) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS localizacao_livro CASCADE; 
CREATE TABLE localizacao_livro (
    pk_localizacao_livro SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(10) NOT NULL UNIQUE, 
    num_prateleira INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS estado CASCADE;
CREATE TABLE estado (
    pk_estado SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS descritores CASCADE;
CREATE TABLE descritores (
    pk_descritores SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS categoria CASCADE;
CREATE TABLE categoria (
    pk_categoria SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS livro CASCADE;
CREATE TABLE livro (
    pk_livro SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    isbn  VARCHAR(50) NOT NULL UNIQUE,
    num_paginas INTEGER,
    num_edicao INTEGER,    
    ano_lancamento INTEGER,
    fk_editora INTEGER NOT NULL,
    fk_estado INTEGER,
    fk_localizacao INTEGER NOT NULL,
    fk_categoria INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_editora) REFERENCES editora(pk_editora) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY( fk_estado ) REFERENCES estado(pk_estado) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_localizacao) REFERENCES localizacao_livro(pk_localizacao_livro) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_categoria) REFERENCES categoria(pk_categoria) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS requisicao CASCADE;
CREATE TABLE requisicao (
    pk_requisicao SERIAL NOT NULL PRIMARY KEY,
    fk_livro INTEGER NOT NULL,
    fk_leitor INTEGER NOT NULL,
    data_requisicao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_entrega TIMESTAMP NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_livro) REFERENCES livro(pk_livro) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_leitor) REFERENCES leitor(pk_leitor) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS livro_autor CASCADE;
CREATE TABLE livro_autor (
    pk_livro_autor SERIAL NOT NULL PRIMARY KEY,
    fk_livro INTEGER NOT NULL,
    fk_autor INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_livro) REFERENCES livro(pk_livro) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_autor) REFERENCES autor(pk_autor) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS livro_descritores CASCADE;
CREATE TABLE livro_descritores (
    pk_livro_descritores SERIAL NOT NULL PRIMARY KEY,
    fk_livro INTEGER NOT NULL,
    fk_descritores INTEGER NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_livro) REFERENCES livro(pk_livro) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (fk_descritores) REFERENCES descritores(pk_descritores) ON UPDATE CASCADE ON DELETE CASCADE
);