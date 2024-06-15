<?php
    include 'functions.php';
  
    // Verificar se foi feita uma pesquisa por sabor de pizza
    if(isset($_GET['search_pizza'])) {
        $search_pizza = $_GET['search_pizza'];
        $contacts_pizza = perform_search('pizza', $search_pizza);
        if(empty($contacts_pizza)) {
            $error_pizza = 'Nenhuma entrega encontrada para o sabor de pizza especificado.';
        }
    }

    // Verificar se foi feita uma pesquisa por nome do cliente
    if(isset($_GET['search_name'])) {
        $search_name = $_GET['search_name'];
        $contacts_name = perform_search('nome', $search_name);
        if(empty($contacts_name)) {
            $error_name = 'Nenhum contato encontrado para o nome especificado.';
        }
    }

    // Verificar se foi feita uma pesquisa por situação
    if(isset($_GET['search_situacao'])) {
        $search_situacao = $_GET['search_situacao'];
        $contacts_situation = perform_search('situacao', $search_situacao);
        if(empty($contacts_situation)) {
            $error_situacao = 'Nenhuma entrega encontrada para a situação especificada.';
        }
    }
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultados da Pesquisa</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.5.2/css/all.css">
</head>
<body>
    <?php template_header('Pizzaria Dom Fernandes'); ?>

    <div class="content read">
        <!-- Formulário de Pesquisa por Sabor de Pizza -->
        <form id="searchPizzaForm" action="" method="get">
            <label for="search_pizza">Pesquisar por sabor de pizza:</label>
            <select id="search_pizza" name="search_pizza">
                <option value="">Selecione o sabor da pizza...</option>
                <?php
                    $pdo = pdo_connect_pgsql();
                    $stmt_pizza_list = $pdo->query('SELECT DISTINCT pizza FROM entregas');
                    while ($row = $stmt_pizza_list->fetch(PDO::FETCH_ASSOC)) {
                        $selected = ($_GET['search_pizza'] ?? '') === $row['pizza'] ? 'selected' : '';
                        echo '<option value="' . htmlspecialchars($row['pizza'], ENT_QUOTES) . '" ' . $selected . '>' . htmlspecialchars($row['pizza'], ENT_QUOTES) . '</option>';
                    }
                ?>
            </select>
            <button class="create-contact" type="submit">Pesquisar</button>
            <button type="button" id="clearPizzaButton">Limpar</button>
        </form>

        <?php if (isset($error_pizza)): ?>
            <p class="error"><?=htmlspecialchars($error_pizza, ENT_QUOTES)?></p>
        <?php elseif (isset($contacts_pizza)): ?>
            <h3>Resultados da Pesquisa por Sabor de Pizza</h3>
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Celular</th>
                        <th>Pizza</th>
                        <th>Situação</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($contacts_pizza as $contact): ?>
                        <tr>
                            <td><?=$contact['id_entregas']?></td>
                            <td><?=$contact['nome']?></td>
                            <td><?=$contact['email']?></td>
                            <td><?=$contact['cel']?></td>
                            <td><?=$contact['pizza']?></td>
                            <td><?=$contact['situacao']?></td>
                            <td class="actions">
                                <a href="alterar_entrega.php?id=<?=$contact['id_entregas']?>" class="edit"><i class="fas fa-pen fa-xs"></i></a>
                                <a href="apagar_entrega.php?id=<?=$contact['id_entregas']?>" class="trash"><i class="fas fa-trash fa-xs"></i></a>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
        <?php endif; ?>
    </div>

    <div class="content read">
        <!-- Formulário de Pesquisa por Nome do Cliente -->
        <form id="searchNameForm" action="" method="get">
            <label for="search_name">Pesquisar por nome do cliente:</label>
            <select id="search_name" name="search_name">
                <option value="">Selecione o nome do cliente...</option>
                <?php
                    $pdo = pdo_connect_pgsql();
                    $stmt_name_list = $pdo->query('SELECT DISTINCT nome FROM contatos');
                    while ($row = $stmt_name_list->fetch(PDO::FETCH_ASSOC)) {
                        $selected = ($_GET['search_name'] ?? '') === $row['nome'] ? 'selected' : '';
                        echo '<option value="' . htmlspecialchars($row['nome'], ENT_QUOTES) . '" ' . $selected . '>' . htmlspecialchars($row['nome'], ENT_QUOTES) . '</option>';
                    }
                ?>
            </select>
            <button class="create-contact" type="submit">Pesquisar</button>
            <button type="button" id="clearNameButton">Limpar</button>
        </form>

        <?php if (isset($error_name)): ?>
            <p class="error"><?=htmlspecialchars($error_name, ENT_QUOTES)?></p>
        <?php elseif (isset($contacts_name)): ?>
            <h3>Resultados da Pesquisa por Nome do Cliente</h3>
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Celular</th>
                        <th>Pizza</th>
                        <th>Situação</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($contacts_name as $contact): ?>
                        <tr>
                            <td><?=$contact['id_entregas']?></td>
                            <td><?=$contact['nome']?></td>
                            <td><?=$contact['email']?></td>
                            <td><?=$contact['cel']?></td>
                            <td><?=$contact['pizza']?></td>
                            <td><?=$contact['situacao']?></td>
                            <td class="actions">
                                <a href="alterar_entrega.php?id=<?=$contact['id_entregas']?>" class="edit"><i class="fas fa-pen fa-xs"></i></a>
                                <a href="apagar_entrega.php?id=<?=$contact['id_entregas']?>" class="trash"><i class="fas fa-trash fa-xs"></i></a>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
        <?php endif; ?>
    </div>

    <div class="content read">
        <!-- Formulário de Pesquisa por Situação -->
        <form id="searchSitForm" action="" method="get">
            <label for="search_situacao">Pesquisar por Situação:</label>
            <select id="search_situacao" name="search_situacao">
                <option value="">Selecione a Situação...</option>
                <option value="cancelada" <?= isset($_GET['search_situacao']) && $_GET['search_situacao'] === 'cancelada' ? 'selected' : '' ?>>Cancelada</option>
                <option value="entregue" <?= isset($_GET['search_situacao']) && $_GET['search_situacao'] === 'entregue' ? 'selected' : '' ?>>Entregue</option>
                <option value="andamento" <?= isset($_GET['search_situacao']) && $_GET['search_situacao'] === 'andamento' ? 'selected' : '' ?>>Em Andamento</option>
            </select>
            <button type="submit">Pesquisar</button>
            <button type="button" id="clearSituationButton">Limpar</button>
        </form>

        <?php if (isset($error_situacao)): ?>
            <p class="error"><?=htmlspecialchars($error_situacao, ENT_QUOTES)?></p>
        <?php elseif (isset($contacts_situation)): ?>
            <h3>Resultados da Pesquisa por Situação</h3>
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Celular</th>
                        <th>Pizza</th>
                        <th>Situação</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($contacts_situation as $contact): ?>
                        <tr>
                            <td><?=$contact['id_entregas']?></td>
                            <td><?=$contact['nome']?></td>
                            <td><?=$contact['email']?></td>
                            <td><?=$contact['cel']?></td>
                            <td><?=$contact['pizza']?></td>
                            <td><?=$contact['situacao']?></td>
                            <td class="actions">
                                <a href="alterar_entrega.php?id=<?=$contact['id_entregas']?>" class="edit"><i class="fas fa-pen fa-xs"></i></a>
                                <a href="apagar_entrega.php?id=<?=$contact['id_entregas']?>" class="trash"><i class="fas fa-trash fa-xs"></i></a>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
        <?php endif; ?>
    </div>

    <script>
        // Limpar seletores de pesquisa ao clicar nos botões "Limpar"
        document.getElementById('clearPizzaButton').addEventListener('click', function() {
            document.getElementById('search_pizza').selectedIndex = 0;
        });

        document.getElementById('clearNameButton').addEventListener('click', function() {
            document.getElementById('search_name').selectedIndex = 0;
        });

        
        document.getElementById('clearsituationButton').addEventListener('click', function() {
            document.getElementById('search_situacao').selectedIndex = 0;
        });
    </script>

    <?php template_footer(); ?>
</body>
</html>
