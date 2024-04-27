-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.

--CREATE DATABASE EXEMPLO_Modelagem_Dados;

CREATE TABLE Cliente (
id_cliente serial PRIMARY KEY,
celular_cliente varchar(15),
nome_cliente varchar(50),
cpf_cliente varchar(14) NOT NULL
);

CREATE TABLE Produto (
valor_produto decimal(7,2),
qtde_produto int,
id_produto serial PRIMARY KEY
);

CREATE TABLE Compra (
id_pedido serial PRIMARY KEY,
data_compra_produto date,
id_produto serial,
id_cliente serial,
FOREIGN KEY(id_produto) REFERENCES Produto (id_produto),
FOREIGN KEY(id_cliente)REFERENCES Cliente (id_cliente)
);

SELECT * FROM CLIENTE, PRODUTO