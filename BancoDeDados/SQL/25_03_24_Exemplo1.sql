

--CREATE DATABASE AULA_SQL;  comando para criar banco de dados

--DROP DATABASE AULA_SQL;  comando para excluir banco de dado

--SELECIONAR COMANDO HÁ EXECUTAR --> CTRL + M ---> CTRL + P 

CREATE DATABASE DB_AULA25MAR24;

CREATE TABLE Fornecedor(
    Fcodigo INT NOT NULL,
    Fnome VARCHAR(30) NOT NULL,
    Status INT,
    Cidade VARCHAR (30)
);

SELECT * FROM Fornecedor;  -- comando para selecionar a tabela e mostrar no "RESULTS"



