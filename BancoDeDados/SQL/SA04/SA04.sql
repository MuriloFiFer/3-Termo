-- Exercicio 1
--DDL

CREATE DATABASE SA04;

CREATE TABLE IF NOT EXISTS Clientes(   
    Nome VARCHAR(30) NOT NULL,
    Sobrenome VARCHAR (30) NOT NULL,
    Email VARCHAR (30) UNIQUE NOT NULL ,
     ID INT PRIMARY KEY
);

SELECT * FROM Clientes;

CREATE TABLE IF NOT EXISTS  Pedidos(    
    ID_Cliente INT ,
    FOREIGN KEY(ID_Cliente) REFERENCES Clientes(ID),
    Data_Pedido DATE NOT NULL,
    TOTAL INT NOT NULL,
    ID INT PRIMARY KEY
    );

   
    ALTER TABLE Pedidos ADD COLUMN Status VARCHAR(30) ;

   
-- DROP TABLE Pedidos;

    SELECT * FROM Pedidos;

--DML

INSERT INTO Clientes (nome, sobrenome, email, id) 
VALUES ('João', 'Silva', 'joao@gmail.com', '1');

INSERT INTO Clientes (nome, sobrenome, email, id) 
VALUES ('Mario', 'Pereira', 'maria@gmail.com', '2');

INSERT INTO Clientes (nome, sobrenome, email, id) 
VALUES ('José', 'Alves', 'jose@gmail.com', '3');





INSERT INTO Pedidos (ID, ID_Cliente, Data_Pedido, TOTAL)
VALUES (1, 1, '2024-04-08', 100);


INSERT INTO Pedidos (ID, ID_Cliente, Data_Pedido, TOTAL)
VALUES (2, 2, '2024-04-08', 150);


INSERT INTO Pedidos (ID, ID_Cliente, Data_Pedido, TOTAL)
VALUES (3, 3, '2024-04-08', 200);


INSERT INTO Pedidos (ID, ID_Cliente, Data_Pedido, TOTAL)
VALUES (4, 1, '2024-04-08', 120);


INSERT INTO Pedidos (ID, ID_Cliente, Data_Pedido, TOTAL)
VALUES (5, 2, '2024-04-08', 180);


UPDATE Pedidos SET TOTAL = 125 WHERE ID = 3;

SELECT * FROM Pedidos;

DELETE FROM Pedidos WHERE ID = 3;

DELETE FROM Clientes WHERE ID = 3;

SELECT * FROM Clientes;

--ALTERAÇÃO TABELA PEDIDOS STATUS

UPDATE Pedidos SET Status = 'Em andamento' WHERE id = 1;

UPDATE Pedidos SET Status = 'Finalizado' WHERE id = 2;

UPDATE Pedidos SET Status = 'Finalizado' WHERE id = 4;

UPDATE Pedidos SET Status = 'Cancelado' WHERE id = 5;

SELECT * FROM Pedidos WHERE STATUS = 'Em andamento';



--Selecionando NOME Data_Pedido e Total dos ultimos 30 dias

-- Seleciona o nome do cliente, a data do pedido e o total
SELECT C.Nome, P.Data_Pedido, P.TOTAL
-- Define a tabela Pedidos como 'P' e a tabela Clientes como 'C'
FROM Pedidos AS P
-- Realiza uma junção entre as tabelas Pedidos e Clientes, relacionando os IDs dos clientes
JOIN Clientes AS C ON P.ID_Cliente = C.ID
-- Filtra apenas os pedidos feitos nos últimos 30 dias
WHERE P.Data_Pedido >= CURRENT_DATE - INTERVAL '30 days';



--Exercicio 2
--DDL

CREATE DATABASE SA04_Ex2;




--Criação da tabela
CREATE TABLE IF NOT EXISTS Produtos(   
    ID INT PRIMARY KEY,
    Nome VARCHAR(30),
    Descição VARCHAR(30),
    Preço  INT   
);



--Seleção da tabela  para vizualizar
SELECT * FROM Produtos;


--criação de restrição para garantir campo PREÇO seja POSITIVO
ALTER TABLE Produtos
ADD CONSTRAINT check_preco_positive CHECK (Preço > 0);


--criação da tabela Pedidos
CREATE TABLE IF NOT EXISTS Pedidos(
    ID INT PRIMARY KEY,
    Data_Pedido DATE,
    Valor INT,
    Status VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS Pedidos_Produtos (
    ID_Pedido INT,
    ID_Produto INT,
    FOREIGN KEY (ID_Pedido) REFERENCES Pedidos(ID),
    FOREIGN KEY (ID_Produto) REFERENCES Produtos(ID)
);


SELECT * FROM Pedidos_Produtos;

--Adicionando Restrição composta
ALTER TABLE Pedidos_Produtos
ADD CONSTRAINT pk_pedidos_produtos PRIMARY KEY (ID_Pedido, ID_Produto);


--criação de index na tabela Produtos
CREATE INDEX idx_nome_produto ON Produtos (Nome);

--Criação da tabela Categorias
CREATE  TABLE IF NOT EXISTS Categorias(
    ID INT PRIMARY KEY,
    Nome VARCHAR(30)
);

SELECT * FROM Categorias;


--Criação da tabela de junção entre Produtos e Categorias
CREATE TABLE IF NOT EXISTS Produtos_Categorias (
    ID_Produto INT,
    ID_Categoria INT,
    FOREIGN KEY (ID_Produto) REFERENCES Produtos(ID),
    FOREIGN KEY (ID_Categoria) REFERENCES Categorias(ID),
    PRIMARY KEY (ID_Produto, ID_Categoria)
);

SELECT * FROM Produtos_Categorias;

--Criação da tabela Funcionarios
CREATE TABLE IF NOT EXISTS Funcionarios(
    ID INT PRIMARY KEY,
    Nome VARCHAR (30),
    sobrenome varchar (30),
    Cargo VARCHAR (30)
);

SELECT * FROM Funcionarios;


--Adicionando Restrição  de verificação de valores validos, função "CHECK"
ALTER TABLE Funcionarios
ADD CONSTRAINT check_cargo_valido 
CHECK (Cargo IN ('Gerente', 'Vendedor', 'Atendente'));


--DML
--Inserção de dados

ALTER TABLE Produtos
RENAME COLUMN Descição TO Descrição;


INSERT INTO Produtos (ID, Nome, Descrição, Preço)
VALUES 
    (1, 'Produto 1', 'Descrição do Produto 1', 10),
    (2, 'Produto 2', 'Descrição do Produto 2', 20),
    (3, 'Produto 3', 'Descrição do Produto 3', 30),
    (4, 'Produto 4', 'Descrição do Produto 4', 40),
    (5, 'Produto 5', 'Descrição do Produto 5', 50);

--alterando valores da tabela Produtos
UPDATE Produtos
SET Nome = 
    CASE 
        WHEN ID = 1 THEN 'Caneta Esferográfica'
        WHEN ID = 2 THEN 'Caderno Universitário'
        WHEN ID = 3 THEN 'Calculadora Científica'
        WHEN ID = 4 THEN 'Mochila Executiva'
        WHEN ID = 5 THEN 'Fone de Ouvido'
    END,
    Descrição = 
    CASE 
        WHEN ID = 1 THEN 'Cor preta'
        WHEN ID = 2 THEN 'capa dura'
        WHEN ID = 3 THEN 'Profissional'
        WHEN ID = 4 THEN 'para laptop'
        WHEN ID = 5 THEN 'Bluetooth'
    END
WHERE ID BETWEEN 1 AND 5;



INSERT INTO Pedidos (ID, Data_Pedido, Valor, Status)
VALUES (1, '2024-01-02', 100, 'Concluido');


INSERT INTO Pedidos (ID, Data_Pedido, Valor, STATUS)
VALUES (2, '2024-03-05', 150, 'Cancelado');

INSERT INTO Pedidos (ID, Data_Pedido, Valor, STATUS)
VALUES (3, '2024-04-08', 200, 'Em andamento');



SELECT * FROM Produtos;
SELECT * FROM Pedidos;
SELECT * FROM Clientes;


INSERT INTO Pedidos_Produtos (ID_Pedido, ID_Produto)
VALUES (1, 1);


INSERT INTO Pedidos_Produtos (ID_Pedido, ID_Produto)
VALUES (1, 2);


INSERT INTO Pedidos_Produtos (ID_Pedido, ID_Produto)
VALUES (2, 3);


INSERT INTO Pedidos_Produtos (ID_Pedido, ID_Produto)
VALUES (3, 4);

INSERT INTO Pedidos_Produtos (ID_Pedido, ID_Produto)
VALUES (3, 5);


SELECT * FROM Pedidos_Produtos;


-- Seleciona todos os campos de produtos
SELECT P.*
-- Define  "P" para a tabela "Produtos"
FROM Produtos P
-- Realiza uma junção entre as tabelas "Produtos" e "Produtos_Categorias"
JOIN Produtos_Categorias PC ON P.ID = PC.ID_Produto
-- Filtra os resultados para incluir apenas os produtos associados à categoria com ID igual a 1
WHERE PC.ID_Categoria = 1;

--atividade realizada até Exercicio 2, DML Ex 5.