-- Active: 1655246663435@@127.0.0.1@3306@dblocadora
CREATE TABLE tb_cidades(  
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    dataFundacao DATE ,
    populacao INT,
    nome VARCHAR(255),
    altitude FLOAT
);

CREATE TABLE tb_paiss(  
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    populacao FLOAT
);