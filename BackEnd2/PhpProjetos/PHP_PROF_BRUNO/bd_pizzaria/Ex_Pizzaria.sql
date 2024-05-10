CREATE DATABASE pizzaria_don_fernandes;

CREATE TABLE IF NOT EXISTS contatos (
id_contato INT NOT NULL PRIMARY KEY,
nome varchar(255) NOT NULL,
email varchar(255) NOT NULL,
cel varchar(255) NOT NULL,
pizza varchar(255) NOT NULL,
cadastro date NOT NULL DEFAULT CURRENT_TIMESTAMP
)
