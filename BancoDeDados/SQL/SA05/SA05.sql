CREATE DATABASE SA05;

--DDL - Atividades

--Ex 1
CREATE TABLE IF NOT EXISTS Clientes(   
      ID INT PRIMARY KEY,
    Nome VARCHAR(30) NOT NULL,
    Sobrenome VARCHAR (30) NOT NULL,
    Email VARCHAR (30) UNIQUE NOT NULL    
);

-- SELECT * FROM Clientes;


--EX 2
CREATE TABLE IF NOT EXISTS  Pedidos(    
    ID INT PRIMARY KEY,
    ID_Cliente INT ,
    FOREIGN KEY(ID_Cliente) REFERENCES Clientes(ID),
    Data_Pedido DATE NOT NULL,
    TOTAL INT NOT NULL    
    );



SELECT * FROM Pedidos;



--Ex 3
CREATE TABLE IF NOT EXISTS Produtos(   
    ID INT PRIMARY KEY,
    Nome VARCHAR(30),
    Descrição VARCHAR(30) NOT NULL,
    Preço  INT
);

 SELECT * FROM Produtos;



ALTER TABLE Produtos
ADD CONSTRAINT check_preco_positive CHECK (Preço > 0);

--Ex 4
CREATE  TABLE IF NOT EXISTS Categorias(
    ID INT PRIMARY KEY,
    Nome VARCHAR(30)
);

-- SELECT * FROM Categorias;

--Ex 5
CREATE TABLE IF NOT EXISTS Pedidos_Produtos (
    ID_Pedido INT,
    ID_Produto INT,
    FOREIGN KEY (ID_Pedido) REFERENCES Pedidos(ID),
    FOREIGN KEY (ID_Produto) REFERENCES Produtos(ID),
    PRIMARY KEY (ID_Pedido, ID_Produto)
);

-- SELECT * FROM Pedidos_Produtos;

--Ex 6
CREATE TABLE IF NOT EXISTS Produtos_Categorias (
    ID_Produto INT,
    ID_Categoria INT,
    FOREIGN KEY (ID_Produto) REFERENCES Produtos(ID),
    FOREIGN KEY (ID_Categoria) REFERENCES Categorias(ID),
    PRIMARY KEY (ID_Produto, ID_Categoria)
);

--Ex 7
CREATE TABLE IF NOT EXISTS Empregados(
    ID INT PRIMARY KEY,
    Nome VARCHAR (30),
    sobrenome varchar (30),
    Cargo VARCHAR (30)
);

--Adicionando Restrição  de verificação de valores validos, função "CHECK"
ALTER TABLE Empregados
ADD CONSTRAINT check_cargo_valido 
CHECK (Cargo IN ('Gerente', 'Vendedor', 'Atendente'));

--Ex 8
CREATE TABLE IF NOT EXISTS Vendas(
    ID INT PRIMARY KEY,
    ID_Produto INT,
    ID_Cliente INT,
    FOREIGN KEY(ID_Produto) REFERENCES Produtos(ID),
    FOREIGN KEY(ID_Cliente) REFERENCES Clientes(ID),
    Data_Venda DATE,
    Quantidade INT 
);

-- SELECT * FROM Vendas;

--Ex 9 -1
ALTER TABLE Clientes
ADD COLUMN TELEFONE  BIGINT;

--SELECT * FROM Clientes;

--Ex 9 -2
ALTER TABLE Produtos
ALTER COLUMN Descrição TYPE VARCHAR(30);

--SELECT * FROM Produtos;

--EX 9 -3
--Drop da Coluna ID_Clite com FOREIGN KEY
--ADD da Coluna ID_Cliente sem FOREIGN KEY
ALTER TABLE Pedidos
DROP COLUMN ID_Cliente;

ALTER TABLE Pedidos
ADD COLUMN ID_Cliente INT;

ALTER TABLE Pedidos
DROP CONSTRAINT pedidos_id_cliente_fkey;

--SELECT * FROM Pedidos;



--EX 9 -4
ALTER TABLE Empregados
RENAME TO  Funcionarios;

--SELECT * FROM Funcionarios;

--DML
--EX 1
INSERT INTO Clientes (ID, NOME, SOBRENOME, EMAIL, TELEFONE)
VALUES (1, 'João', 'ALVES', 'joão@gmail.com', 1988445566);

INSERT INTO Clientes (ID, NOME, SOBRENOME, EMAIL, TELEFONE)
VALUES (2, 'Maria', 'Silva', 'maria@gmail.com', 1987775566);

INSERT INTO Clientes (ID, NOME, SOBRENOME, EMAIL, TELEFONE)
VALUES (3, 'Pedro', 'Fernandez', 'pedrof@gmail.com', 1985993322);

INSERT INTO Clientes (ID, NOME, SOBRENOME, EMAIL, TELEFONE)
VALUES (4,'Lucas','Ramos','lucas@gmail.com',1984556677);

INSERT INTO Clientes (ID, NOME, SOBRENOME, EMAIL, TELEFONE)
VALUES (5, 'Karol', 'Ribeiro', 'karol@gmail.com', 1983221456);


SELECT * FROM Clientes;

--EX 2
INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (1, '2022-05-01', 5, 1);

INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (2, '2021-05-01', 3, 3);

INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (3, '2024-04-11', 10, 2);

INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (4, '2023-10-05', 2, 1);

INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (5, '2024-04-15', 4, 5);

INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (6, '2021-07-11', 7, 3);

INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (7, '2024-04-10', 6, 2);

INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (8, '2023-05-20', 5, 1);

INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (9, '2023-05-25', 8, 4);

INSERT INTO Pedidos (ID, data_pedido, total, id_cliente)
VALUES (10, '2023-07-09', 9, 5);

-- SELECT * FROM Pedidos;

--Ex 3
INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (1, 'Camiseta Azul', 'Camiseta de algodão azul', 15.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (2, 'Calça Jeans', 'Calça jeans masculina', 29.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (3, 'Tênis Esportivo', 'Tênis esportivo leve', 49.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (4, 'Bolsa de Couro', 'Bolsa de couro marrom', 39.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (5, 'Relógio Analógico', 'Relógio analógico com p', 79.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (6, 'Óculos de Sol', 'Óculos de sol estilo wa', 24.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (7, 'Jaqueta de Couro', 'Jaqueta de couro preta', 69.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (8, 'Mochila Escolar', 'Mochila escolar resisten', 34.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (9, 'Cinto de Couro', 'Cinto de couro marrom c', 14.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (10, 'Chapéu de Palha', 'Chapéu de palha natural', 9.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (11, 'Moletom Cinza', 'Moletom de algodão cinza', 49.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (12, 'Sapatênis Casual', 'Sapatênis casual masculino', 39.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (13, 'Bolsa Térmica', 'Bolsa térmica para alimentos', 19.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (14, 'Perfume Floral', 'Perfume floral com notas de', 29.99);

INSERT INTO Produtos (ID, nome, descrição, preço)
VALUES (15, 'Brinco de Prata', 'Brinco de prata com zircôn', 14.99);


-- SELECT * FROM PRODUTOS;

--Ex 4
INSERT INTO Pedidos_Produtos (id_pedido, id_produto) VALUES (1, 1);

INSERT INTO Pedidos_Produtos (id_pedido, id_produto) VALUES (1, 2);

INSERT INTO Pedidos_Produtos (id_pedido, id_produto) VALUES (1, 3);


INSERT INTO Pedidos_Produtos (id_pedido, id_produto) VALUES (2, 4);

INSERT INTO Pedidos_Produtos (id_pedido, id_produto) VALUES (2, 5);

INSERT INTO Pedidos_Produtos (id_pedido, id_produto) VALUES (2, 6);


INSERT INTO Pedidos_Produtos (id_pedido, id_produto) VALUES (3, 7);

INSERT INTO Pedidos_Produtos (id_pedido, id_produto) VALUES (3, 8);

INSERT INTO Pedidos_Produtos (id_pedido, id_produto) VALUES (3, 9);

SELECT * FROM Pedidos_Produtos;

-- Camiseta Azul, Calça Jeans e Jaqueta de Couro à categoria "Vestuário"
INSERT INTO Produtos_Categorias (ID_Produto, ID_Categoria)
VALUES (1, 1), (2, 1), (7, 1);

-- Tênis Esportivo, Óculos de Sol e Chapéu de Palha à categoria "Acessórios"
INSERT INTO Produtos_Categorias (ID_Produto, ID_Categoria)
VALUES (3, 2), (6, 2), (10, 2);

-- Bolsa de Couro, Mochila Escolar e Bolsa Térmica à categoria "Bolsas e Malas"
INSERT INTO Produtos_Categorias (ID_Produto, ID_Categoria)
VALUES (4, 3), (8, 3), (13, 3);

-- Relógio Analógico, Perfume Floral e Brinco de Prata à categoria "Acessórios Pessoais"
INSERT INTO Produtos_Categorias (ID_Produto, ID_Categoria)
VALUES (5, 4), (14, 4), (15, 4);

-- Cinto de Couro, Moletom Cinza e Sapatênis Casual à categoria "Roupas Casuais"
INSERT INTO Produtos_Categorias (ID_Produto, ID_Categoria)
VALUES (9, 5), (11, 5), (12, 5);

SELECT * FROM Produtos_Categorias;



