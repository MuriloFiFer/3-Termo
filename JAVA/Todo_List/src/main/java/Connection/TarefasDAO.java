package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Tarefas;

public class TarefasDAO {

    // Atributos
    private Connection connection;
    private List<Tarefas> tarefas;

    // Construtor
    public TarefasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // Métodos do CRUD
    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (MARCA VARCHAR(255), MODELO VARCHAR(255), ANO VARCHAR(255), PLACA VARCHAR(255) PRIMARY KEY, VALOR VARCHAR(255))";

        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Listar todos os valores cadastrados
    public List<Tarefas> listarTodos() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta
        tarefas = new ArrayList<>();
        // Cria uma lista para armazenar as tarefas recuperadas do banco de dados
        try {
            stmt = connection.prepareStatement("SELECT * FROM tarefas");
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Tarefas com os valores do registro
                Tarefas tarefa = new Tarefas(
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("ano"),
                        rs.getString("placa"),
                        rs.getString("valor"));
                tarefas.add(tarefa); // Adiciona o objeto Tarefas à lista de tarefas
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return tarefas; // Retorna a lista de tarefas recuperadas do banco de dados
    }

    // Cadastrar Tarefa no banco
    public void cadastrar(String marca, String modelo, String ano, String placa, String valor) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO tarefas (marca, modelo, ano, placa, valor) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, marca);
            stmt.setString(2, modelo);
            stmt.setString(3, ano);
            stmt.setString(4, placa);
            stmt.setString(5, valor);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Atualizar dados no banco
    public void atualizar(String marca, String modelo, String ano, String placa, String valor) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa
        String sql = "UPDATE tarefas SET marca = ?, modelo = ?, ano = ?, valor = ? WHERE placa = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, marca);
            stmt.setString(2, modelo);
            stmt.setString(3, ano);
            stmt.setString(4, valor);
            // placa é chave primária não pode ser alterada.
            stmt.setString(5, placa);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Apagar dados do banco
    public void apagar(String placa) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pela placa
        String sql = "DELETE FROM tarefas WHERE placa = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, placa);
            stmt.executeUpdate(); // Executa a instrução SQL
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

}
