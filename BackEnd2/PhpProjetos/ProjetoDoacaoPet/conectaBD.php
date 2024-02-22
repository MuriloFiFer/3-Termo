<?php
// endereco
// nome do BD
// usuario
// senha
$endereco = 'localhost';
$banco = 'postgres';
$adm = 'postgres';
$senha = 'postgres7210';

try {
    // sgbd:host;port;dbname
    // usuario
    // senha
    // errmode
    $pdo = new PDO(
        "pgsql:host=$endereco;port=5432;dbname=$banco",$adm,$senha,[PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]
    );  
    echo "Conectado no banco de dados!!!";
     
    //codigo sql para criar as tabelas
    //criação da tabela usuario
    $sql = "CREATE TABLE IF NOT EXISTS usuario (ID SERIAL, NOME varchar(255), DATA_NASCIMENTO varchar(255), TELEFONE varchar(255), EMAIL varchar(255), SENHA varchar(255))";
    $stmt = $pdo->prepare($sql);
    $stmt->execute();
    //criação da tabela anuncio
    $sql = "CREATE TABLE IF NOT EXISTS anuncio (ID SERIAL PRIMARY KEY, FASE varchar(255), TIPO varchar(255), PORTE varchar(255), SEXO varchar(255), PELAGEM_COR varchar(255), RACA varchar(255), OBSERVACAO varchar(255), EMAIL_USUARIO varchar(255))";
    $stmt = $pdo->prepare($sql);
    $stmt->execute();

} catch (PDOException $e){
    echo "Falha ao conectar ao banco de dados.<br\>";
        die ($e->getMessage());  
    } 
