<?php
// endereco
// nome do BD
// usuario
// senha
$endereco = 'localhost';
$banco = 'postgres';
$usuario = 'postgres';
$senha = 'postgres7210';
try {
// sgbd:host;port;dbname
// usuario
// senha
// errmode
$pdo = new PDO("pgsql:host=$endereco;port=5432;dbname=$banco", $usuario, $senha,
[PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]);
echo "Conectado no banco de dados!!!";
$sql = "CREATE TABLE IF NOT EXISTS USUARIO
(nome VARCHAR(255), data_nascimento VARCHAR(255), telefone VARCHAR(255), email VARCHAR(255) PRIMARY KEY VARCHAR(255),senha VARCHAR(255))";
$pdo->exec($sql);
} catch (PDOException $e) {
echo "Falha ao conectar ao banco de dados. <br/>";
die($e->getMessage());
}
?>