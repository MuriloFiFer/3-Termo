<?php
// Função para conectar ao banco de dados PostgreSQL
function pdo_connect_pgsql() {
    $DATABASE_HOST = 'localhost';
    $DATABASE_PORT = '5432';
    $DATABASE_USER = 'postgres';
    $DATABASE_PASS = 'postgres';
    $DATABASE_NAME = 'pizzaria_don_fernandes';
    
    try {
        $pdo = new PDO('pgsql:host=' . $DATABASE_HOST . ';port=' . $DATABASE_PORT . ';dbname=' . $DATABASE_NAME . ';user=' . $DATABASE_USER . ';password=' . $DATABASE_PASS);
        
        // Define o modo de erro para Exception para que os erros sejam lançados e possam ser capturados.
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        
        return $pdo;
    } catch (PDOException $exception) {
        // Log do erro ou exibição de mensagem mais detalhada.
        $errorDetails = 'Error details: ' . $exception->getMessage() . ' Code: ' . $exception->getCode();
        error_log('Failed to connect to database: ' . $errorDetails);
        exit('Failed to connect to database. Check error log for details. Debug: ' . $errorDetails);
    }
}

// Função para executar a pesquisa de entregas
function perform_search($field, $query) {
    // Conexão com o banco de dados
    $pdo = pdo_connect_pgsql();
    
    // Consulta SQL base
    $sql = "SELECT * FROM entregas WHERE $field LIKE :query";
    
    // Preparar a consulta
    $stmt = $pdo->prepare($sql);
    
    // Substituir parâmetros na consulta
    $stmt->bindValue(':query', '%' . $query . '%', PDO::PARAM_STR);
    
    // Executar a consulta
    $stmt->execute();
    
    // Obter os resultados da consulta
    $results = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
    return $results;
}

// Função para gerar o cabeçalho comum para as páginas HTML
function template_header($title) {
    echo <<<EOT
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>$title</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.5.2/css/all.css">
</head>
<body>
    <nav class="navtop">
        <div>
            <img src="img/img1.jfif" alt="LOGO" id="imgLogo1">
            <h1> Pizzaria Dom Fernandes </h1>
            <a href="index.php"><i class="fas fa-home"></i>Inicio</a>
            <a href="read.php"><i class="fas fa-shopping-basket"></i>Pedidos</a>
            <a href="ler_entregas.php"><i class="fa-solid fa-motorcycle"></i>Entregas</a>
            <a href="pesquisar.php"><i class="fa-solid fa-magnifying-glass"></i>Pesquisar</a>
            <a href="processar_pedido.php"><i class="fa-solid fa-cart-shopping"></i>Comprar</a>
        </div>
    </nav>
EOT;
}

// Função para gerar o rodapé comum para as páginas HTML
function template_footer() {
    echo <<<EOT
    </body>
</html>
EOT;
}
?>
