CREATE DATABASE pizzaria_don_fernandes;

CREATE TABLE IF NOT EXISTS contatos (
id_contato INT NOT NULL PRIMARY KEY,
nome varchar(255) NOT NULL,
email varchar(255) NOT NULL,
cel varchar(255) NOT NULL,
pizza varchar(255) NOT NULL,
cadastro date NOT NULL DEFAULT CURRENT_TIMESTAMP
)


INSERT INTO contatos (id_contato, nome, email, cel, pizza, cadastro) VALUES

SELECT * from contatos

SELECT * FROM contatos ORDER BY id_contato OFFSET :offset 1 :1

SELECT COUNT(*) FROM contatos

ALTER TABLE contatos
RENAME COLUMN id TO id_contato


SELECT * FROM contatos WHERE id_contato = ?

CREATE TABLE IF NOT EXISTS entregas (
id_contato INT NOT NULL PRIMARY KEY,
nome varchar(255) NOT NULL,
pizza varchar(255) NOT NULL,
cadastro date NOT NULL DEFAULT CURRENT_TIMESTAMP,
status varchar(255) NOT NULL
)

SELECT * from entregas


DROP TABLE entregas 