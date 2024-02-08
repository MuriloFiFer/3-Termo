package todolist.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import todolist.Model.Task;

/**
 * ConnectionDAO
 */
public class ListDAO {

    private Connection connection;
    private List<Task> tarefa;

    public ListDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS todo_list (id INT AUTO_INCREMENT PRIMARY KEY, tarefa VARCHAR(255), concluida ENUM('C', 'P'))";

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
    public List<Task> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        tarefa = new ArrayList<>();

        try {
            String query = "SELECT * FROM todo_list";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Task tarefas = new Task(rs.getString("tarefa"));
                tarefa.add(tarefas);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return tarefa;
    }

    // Cadastrar Tarefa no banco
    public void cadastrar(String tarefa, String concluida) {
        PreparedStatement stmt = null;
        String query = "INSERT INTO todo_list (tarefa, concluida) VALUES (?, ?)";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, tarefa);
            stmt.setString(2, concluida);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Atualizar dados no banco
    public void atualizar(String tarefa, String concluida, String id) {
        PreparedStatement stmt = null;
        String query = "UPDATE todo_list SET tarefa = ?, concluida = ? WHERE id = ?";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, tarefa);
            stmt.setString(2, concluida);
            stmt.setString(3, id);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);

        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Apagar dados do banco
    public void apagar(String id) {
        PreparedStatement stmt = null;
        String query = "DELETE FROM todo_list WHERE id = ?";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
