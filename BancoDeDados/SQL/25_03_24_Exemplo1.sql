

--CREATE DATABASE AULA_SQL;  comando para criar banco de dados

--DROP DATABASE AULA_SQL;  comando para excluir banco de dado

--SELECIONAR COMANDO HÁ EXECUTAR --> CTRL + M ---> CTRL + P 

CREATE DATABASE DB_AULA25MAR24;

CREATE TABLE Fornecedor(
    Fcodigo INT NOT NULL,  --NOT NULL, VALOR NÃO PODE SER VAZIO
    Fnome VARCHAR(30) NOT NULL,
    Status INT,
    Cidade VARCHAR (30)
);

SELECT * FROM Fornecedor;  -- comando para selecionar a tabela e mostrar no "RESULTS"


--cria a tabela se não existir 
-- se existir nada acontece, inclusive erro
CREATE TABLE IF NOT EXISTS Fornecedor(
    Fcodigo INT NOT NULL,
    Fnome VARCHAR(30) NOT NULL,
    Status INT,
    Cidade VARCHAR (30)
);

CREATE TABLE IF NOT EXISTS Departamento(
    Dcodigo INT NOT NULL,
    Dnome VARCHAR(30) NOT NULL,   
    Cidade VARCHAR (30),
    PRIMARY KEY (Dcodigo)
);

SELECT * FROM Departamento; 


-- criação da tabela com Foreing Key (utiliza referencia de outra tabela)
CREATE TABLE Empregado
(
    Ecodigo INT NOT NULL,   
    Enome VARCHAR(40)NOT NULL,
    CPF VARCHAR(15)NOT NULL,
    Salario DECIMAL(7,2),
    Dcodigo INT NOT NULL,
    PRIMARY KEY(Ecodigo,Enome),
    FOREIGN KEY(Dcodigo) REFERENCES Departamento
)


SELECT * FROM Empregado; 


--Alterar Tabelas com o Alter Table
--Adicionando coluna sexo
ALTER TABLE Empregado ADD sexo CHAR(1);

--Excluindo coluna sexo
ALTER TABLE Empregado DROP COLUMN sexo;

--Renomear a tabela
ALTER TABLE Empregado RENAME TO funcionario;


--Altera o tipo da tabela --ALTER COLLUM
ALTER TABLE Fornecedor ALTER COLLUM status TYPE INT ativo TYPE BOOLEAN;





