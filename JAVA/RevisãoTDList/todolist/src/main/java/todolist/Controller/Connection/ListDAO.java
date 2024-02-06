package todolist.Controller.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import todolist.Model.Task;

/**
 * ConnectioDAO
 */
public class ListDAO {

    private Connection connection;
    private List<Task> tarefa;

    public ListDAO() {
        this.connection = ConnectionFactory.getConnection();

    }

   // Método para criar a tabela no banco de dados
public void criaTabela() {
    // Define a instrução SQL para criar a tabela MINHA_TABELA se não existir
    String sql = "CREATE TABLE IF NOT EXISTS tarefas_todolist (ID SERIAL PRIMARY KEY,NOME    VARCHAR(255),EMAIL VARCHAR(255)";
    try (Statement stmt = this.connection.createStatement()) {
    stmt.execute(sql); // Executa a instrução SQL
    System.out.println("Tabela criada com sucesso.");
    } catch (SQLException e) {
    // Captura exceções relacionadas a erros no banco de dados
    throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
    } finally {
    ConnectionFactory.closeConnection(this.connection); // Fecha a conexão
    }
    }

    // Listar todos os valores cadastrados
    public List<Task> listarTodos() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta
        tarefa = new ArrayList<>();
        // Cria uma lista para armazenar oas tarefas recuperados do banco de dados
        try {
            String query = "SELECT * FROM tarefas_todolist";
            stmt = connection.prepareStatement(query);
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Carros com os valores do
                // registro
                Task tarefas = new Task(
                        rs.getString("Tarefa"));
                tarefa.add(tarefas); // Adiciona o objeto Carros à lista de carros
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection);
            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return tarefa; // Retorna a lista de carros recuperados do banco de dados
    }

    // Cadastrar Carro no banco
    public void cadastrar(String tarefa, String concluida) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String query = "INSERT INTO tarefas_todolist (tarefa, concluida, id) VALUES (?, ?)";

        try {
            stmt = connection.prepareStatement(query);

            stmt.setString(1, tarefa);
            stmt.setString(2, concluida);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);

        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }

    // Atualizar dados no banco
    public void atualizar(String tarefa, String concluida, String id) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa
        String query = "UPDATE tarefas_todolist SET tarefa = ?, concluida = ? WHERE id = ?"; 

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, tarefa);
            stmt.setString(2, concluida);
            // placa é chave primaria não pode ser alterada
            stmt.setString(3, id);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);

        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }

     // Apagar dados do banco
     public void apagar(String id) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pela id
        String query = "DELETE FROM tarefas_todolist WHERE id = ?";
        
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate(); // Executa a instrução SQL
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } 
        ConnectionFactory.closeConnection(connection);
    }

}