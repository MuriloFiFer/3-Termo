create database ATV1_Pizzaria

CREATE TABLE IF NOT EXISTS contatos ( 
    id_contato INT NOT NULL PRIMARY KEY,
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    cel varchar(255) NOT NULL,
    pizza varchar(255) NOT NULL,
    cadastro date NOT NULL DEFAULT CURRENT_TIMESTAMP
    )

   CREATE TABLE IF NOT EXISTS entregas ( 
    id_entregas INT NOT NULL PRIMARY KEY,
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    cel varchar(255) NOT NULL,
    pizza varchar(255) NOT NULL,
    cadastro date NOT NULL DEFAULT CURRENT_TIMESTAMP,
    situacao VARCHAR(30)
    )

    --criar a tabela pedido
    CREATE TABLE IF NOT EXISTS pedido (
        id_pedido SERIAL PRIMARY KEY,
        id_entregas INT NOT NULL,
        id_contato INT NOT NULL,
        id_pizza INT NOT NULL,
        data_pedido DATE NOT NULL,
        CONSTRAINT fk_id_entregas FOREIGN KEY (id_entregas) REFERENCES entregas (id_entregas),
        CONSTRAINT fk_id_pizza FOREIGN KEY (id_pizza) REFERENCES pizzas (id_pizza),
        CONSTRAINT fk_id_contato FOREIGN KEY (id_contato) REFERENCES contatos (id_contato)
    );

CREATE TABLE IF NOT EXISTS pizzas (
    id_pizza SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    ingredientes TEXT NOT NULL
);


--PARTE 1

--Ex-1
SELECT 
    pedido.id_pedido,
    pedido.data_pedido,
    contatos.nome AS nome_cliente,
    contatos.email AS email_cliente,
    contatos.cel AS cel_cliente,
    pizzas.nome AS nome_pizza,
    pizzas.preco AS preco_pizza,
    entregas.situacao AS situacao_entrega
FROM 
    pedido
INNER JOIN 
    contatos ON pedido.id_contato = contatos.id_contato
INNER JOIN 
    pizzas ON pedido.id_pizza = pizzas.id_pizza
INNER JOIN 
    entregas ON pedido.id_entregas = entregas.id_entregas;


--Ex-2
SELECT 
    pedido.id_pedido,
    pedido.data_pedido,
    pizzas.nome AS nome_pizza,
    pizzas.preco AS preco_pizza,
    pizzas.ingredientes AS ingredientes_pizza
FROM 
    pedido
INNER JOIN 
    pizzas ON pedido.id_pizza = pizzas.id_pizza;


--Ex-3
CREATE TABLE IF NOT EXISTS funcionarios (
    id_funcionario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cel VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS atribuicoes (
    id_atribuicao SERIAL PRIMARY KEY,
    id_funcionario INT NOT NULL,
    area_trabalho VARCHAR(255) NOT NULL,
    CONSTRAINT fk_id_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionarios (id_funcionario)
);


SELECT 
    funcionarios.id_funcionario,
    funcionarios.nome AS nome_funcionario,
    funcionarios.email AS email_funcionario,
    funcionarios.cel AS cel_funcionario,
    atribuicoes.area_trabalho AS area_trabalho
FROM 
    funcionarios
INNER JOIN 
    atribuicoes ON funcionarios.id_funcionario = atribuicoes.id_funcionario;

 --Ex-4
 CREATE TABLE IF NOT EXISTS pedidos_status (
    id_pedido INT NOT NULL PRIMARY KEY,
    status VARCHAR(255) NOT NULL,
    id_funcionario INT NOT NULL,
    CONSTRAINT fk_id_pedido FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido),
    CONSTRAINT fk_id_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionarios (id_funcionario)
);

SELECT 
    pedido.id_pedido,
    pedido.data_pedido,
    pedidos_status.status,
    funcionarios.nome AS nome_funcionario,
    funcionarios.email AS email_funcionario
FROM 
    pedido
INNER JOIN 
    pedidos_status ON pedido.id_pedido = pedidos_status.id_pedido
INNER JOIN 
    funcionarios ON pedidos_status.id_funcionario = funcionarios.id_funcionario;

   
--Ex-5
SELECT 
    contatos.id_contato,
    contatos.nome AS nome_cliente,
    contatos.email AS email_cliente,
    contatos.cel AS cel_cliente,
    pedido.id_pedido,
    pedido.data_pedido
FROM 
    contatos
INNER JOIN 
    pedido ON contatos.id_contato = pedido.id_contato;

    --Ex-6
    SELECT 
    pizzas.id_pizza,
    pizzas.nome AS nome_pizza,
    pizzas.preco AS preco_pizza,
    pizzas.ingredientes AS ingredientes_pizza
FROM 
    pizzas;

    --Ex-7
    SELECT 
    pedido.id_pedido,
    pedido.data_pedido,
    entregas.nome AS nome_entrega,
    entregas.email AS email_entrega,
    entregas.cel AS cel_entrega,
    entregas.situacao AS situacao_entrega
FROM 
    pedido
INNER JOIN 
    entregas ON pedido.id_entregas = entregas.id_entregas;

    --Ex-8
CREATE TABLE IF NOT EXISTS supervisores (
    id_funcionario INT NOT NULL,
    id_supervisor INT NOT NULL,
    CONSTRAINT fk_id_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionarios (id_funcionario),
    CONSTRAINT fk_id_supervisor FOREIGN KEY (id_supervisor) REFERENCES funcionarios (id_funcionario)
);

SELECT 
    f1.id_funcionario AS id_funcionario,
    f1.nome AS nome_funcionario,
    f2.id_funcionario AS id_supervisor,
    f2.nome AS nome_supervisor
FROM 
    funcionarios f1
INNER JOIN 
    supervisores ON f1.id_funcionario = supervisores.id_funcionario
INNER JOIN 
    funcionarios f2 ON supervisores.id_supervisor = f2.id_funcionario;

    --Ex-9
CREATE TABLE IF NOT EXISTS tamanhos (
    id_pizza INT NOT NULL,
    tamanho VARCHAR(50) NOT NULL,
    CONSTRAINT fk_id_pizza FOREIGN KEY (id_pizza) REFERENCES pizzas (id_pizza)
);


SELECT 
    pedido.id_pedido,
    pedido.data_pedido,
    pizzas.nome AS nome_pizza,
    tamanhos.tamanho AS tamanho_pizza
FROM 
    pedido
INNER JOIN 
    pizzas ON pedido.id_pizza = pizzas.id_pizza
INNER JOIN 
    tamanhos ON pizzas.id_pizza = tamanhos.id_pizza;


    --Ex-10
CREATE TABLE IF NOT EXISTS promocoes (
    id_pizza INT NOT NULL,
    descricao_promocao VARCHAR(255) NOT NULL,
    CONSTRAINT fk_id_pizza FOREIGN KEY (id_pizza) REFERENCES pizzas (id_pizza)
);


SELECT 
    pizzas.id_pizza,
    pizzas.nome AS nome_pizza,
    pizzas.preco AS preco_pizza,
    promocoes.descricao_promocao
FROM 
    pizzas
LEFT JOIN 
    promocoes ON pizzas.id_pizza = promocoes.id_pizza;

--PARTE 2
--EX1
SELECT DISTINCT 
    contatos.id_contato,
    contatos.nome AS nome_cliente,
    contatos.email AS email_cliente,
    contatos.cel AS cel_cliente
FROM 
    contatos
INNER JOIN 
    pedido ON contatos.id_contato = pedido.id_contato;

--EX2
SELECT 
    id_pedido,
    id_contato,
    id_entregas,
    id_pizza,
    data_pedido
FROM 
    pedido
WHERE 
    data_pedido BETWEEN '2024-01-01' AND '2024-12-31';

--EX3
SELECT 
    pedido.id_pedido,
    pizzas.nome AS nome_item,
    pizzas.preco AS preco_item
FROM 
    pedido
INNER JOIN 
    pizzas ON pedido.id_pizza = pizzas.id_pizza
WHERE 
    pedido.id_pedido = 1; -- Substitua 1 pelo ID do pedido específico

--EX4
SELECT 
    contatos.id_contato,
    contatos.nome AS nome_cliente,
    SUM(pizzas.preco) AS total_gasto
FROM 
    pedido
INNER JOIN 
    contatos ON pedido.id_contato = contatos.id_contato
INNER JOIN 
    pizzas ON pedido.id_pizza = pizzas.id_pizza
WHERE 
    contatos.id_contato = 1 -- Substitua 1 pelo ID do cliente específico
GROUP BY 
    contatos.id_contato,
    contatos.nome;

--EX5
SELECT 
    pizzas.nome AS nome_pizza,
    COUNT(pedido.id_pizza) AS total_pedidos
FROM 
    pedido
INNER JOIN 
    pizzas ON pedido.id_pizza = pizzas.id_pizza
GROUP BY 
    pizzas.nome
ORDER BY 
    total_pedidos DESC;

--EX6
SELECT 
    nome,
    preco,
    ingredientes
FROM 
    pizzas
WHERE 
    nome = 'Margherita'; -- Substitua 'Margherita' pelo nome da pizza específica

--EX7
SELECT 
    id_funcionario,
    nome AS nome_funcionario,
    email AS email_funcionario,
    cel AS cel_funcionario
FROM 
    funcionarios;

--EX8
SELECT 
    horario_abertura,
    horario_fechamento
FROM 
    horario_funcionamento;

--EX9
SELECT 
    pedido.id_pedido,
    pedido.data_pedido,
    entregas.situacao
FROM 
    pedido
INNER JOIN 
    entregas ON pedido.id_entregas = entregas.id_entregas
WHERE 
    entregas.situacao = 'Em andamento'; -- ou outra condição que define um pedido em andamento

--EX10
SELECT 
    AVG(EXTRACT(EPOCH FROM AGE(data_pedido, CURRENT_TIMESTAMP))) AS tempo_medio_espera
FROM 
    pedido;
