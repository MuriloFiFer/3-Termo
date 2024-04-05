-- Criação do banco de dados
CREATE DATABASE DB_Exercicios_DDL;

-- Criação da tabela FORNECEDOR
CREATE TABLE FORNECEDOR(
    FCODIGO INTEGER PRIMARY KEY,
    FNOME VARCHAR(50) NOT NULL,
    STATUS VARCHAR(10) DEFAULT 'ATIVO', 
    CIDADE VARCHAR(20)
);
-- Coluna de status com valor padrão 'ATIVO'

-- Visualização dos dados na tabela FORNECEDOR
SELECT * FROM FORNECEDOR;

-- Criação da tabela PECA
CREATE TABLE PECA(
    PCODIGO INTEGER PRIMARY KEY,
    PNOME VARCHAR(50) NOT NULL,
    COR VARCHAR(10) NOT NULL,
    PESO NUMERIC(7,2) NOT NULL, 
    CIDADE VARCHAR(20) NOT NULL
);
-- Coluna para armazenar o peso numérico

-- Visualização dos dados na tabela PECA
SELECT * FROM PECA;

-- Criação da tabela INSTITUCAO
CREATE TABLE INSTITUCAO(
    ICODIGO INTEGER PRIMARY KEY,
    INOME VARCHAR(50) NOT NULL
);

-- Visualização dos dados na tabela INSTITUCAO
SELECT * FROM INSTITUCAO;

-- Criação da tabela PROJETOS
CREATE TABLE PROJETOS(
    PRCODIGO INTEGER PRIMARY KEY,
    PRNOME VARCHAR(50) NOT NULL,
    PRCIDADE VARCHAR(20) NOT NULL,
    ICODIGO INTEGER,
    FOREIGN KEY (ICODIGO) REFERENCES INSTITUCAO  
);
-- Chave estrangeira referenciando a tabela INSTITUCAO


-- Visualização dos dados na tabela PROJETOS
SELECT * FROM PROJETOS;

-- Criação da tabela FORNECIMENTO
CREATE TABLE FORNECIMENTO(
    ID SERIAL PRIMARY KEY, 
    FCODIGO INTEGER,
    PCODIGO INTEGER,
    PRCODIGO INTEGER,
    QUANTIDADE INTEGER,
    FOREIGN KEY(FCODIGO) REFERENCES FORNECEDOR, 
    FOREIGN KEY(PCODIGO) REFERENCES PECA,
    FOREIGN KEY(PRCODIGO) REFERENCES PROJETOS
);
-- Coluna de identificação com auto-incremento
-- Chave estrangeira referenciando a tabela FORNECEDOR
 -- Chave estrangeira referenciando a tabela PECA
  -- Chave estrangeira referenciando a tabela PROJETOS



-- Visualização dos dados na tabela FORNECIMENTO
SELECT * FROM FORNECIMENTO;
