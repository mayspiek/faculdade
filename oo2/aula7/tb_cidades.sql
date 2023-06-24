
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