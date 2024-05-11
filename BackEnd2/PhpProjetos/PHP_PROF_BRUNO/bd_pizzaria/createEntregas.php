
<?php
include 'functions.php';
$pdo = pdo_connect_pgsql();
$msg = '';
// Verifica se os dados POST não estão vazios
if (!empty($_POST)) {
    // Se os dados POST não estiverem vazios, insere um novo registro
    // Configura as variáveis que serão inserid_contatoas. Devemos verificar se as variáveis POST existem e, se não existirem, podemos atribuir um valor padrão a elas.
    $id_contato = isset($_POST['id_contato']) && !empty($_POST['id_contato']) && $_POST['id_contato'] != 'auto' ? $_POST['id_contato'] : NULL;
    // Verifica se a variável POST "nome" existe, se não existir, atribui o valor padrão para vazio, basicamente o mesmo para todas as variáveis
    $nome = isset($_POST['nome']) ? $_POST['nome'] : '';   
    $pizza = isset($_POST['pizza']) ? $_POST['pizza'] : '';
    $cadastro = isset($_POST['cadastro']) ? $_POST['cadastro'] : date('Y-m-d H:i:s');
    $status = isset($_POST['status']) ? $_POST['status'] : '';
    // Insere um novo registro na tabela contacts
    $stmt = $pdo->prepare('INSERT INTO entregas (id_contato, nome, pizza, cadastro, status) VALUES (?, ?, ?, ?, ?)');
    $stmt->execute([$id_contato, $nome, $pizza, $cadastro, $status]);
    // Mensagem de saída
    $msg = 'Entrega Realizado com Sucesso!';
}
?>


<?=template_header('Cadastro de Entregas')?>

<div class="content update">
	<h2>Registrar Entrega</h2>
    <form action="createEntregas.php" method="post">
        <label for="id_contato">ID</label>
        <label for="nome">Nome</label>
        <input type="text" name="id_contato" placeholder="" value="" id_contato="id_contato" >
        <input type="text" name="nome" placeholder="Seu Nome" id_contato="nome">
        <label for="pizza">Sabor Pizza</label>
        <input type="text" name="pizza" placeholder="Pizza" id_contato="pizza">
        <label for="cadastro">Data da Entrega</label>
        <input type="datetime-local" name="Criar" value="<?=date('Y-m-d\TH:i')?>" id_contato="cadastro">           
        <select name="status" id="status-select">
        <option value="">--Status--</option>
        <option value="Entregue">Entrege</option>
        <option value="NãoEntregue">Não Entregue</option>
                        
        <input type="submit" value="Realizar Entrega">
       
    </form>
    <?php if ($msg): ?>
    <p><?=$msg?></p>
    <?php endif; ?>
</div>

<?=template_footer()?>
