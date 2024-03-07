package webapp.newsletterjdbc.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.cache.support.NoOpCacheManager;

public class CadastroDAO {
     // atributo
    private Connection connection;

    // construtor
    public CadastroDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criarTabela() {

        String sql = "CREATE TABLE IF NOT EXISTS Cadastro_spring (ID SERIAL PRIMARY KEY, NOME VARHCAR(255),EMAIL VARCHAR(255), TELEFONE VARCHAR(14), SENHA VARCHAR(30))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Cadastrar Carro no banco
    public void cadastrar(String nome,String email, String tel, String senha) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO newsletter_spring (nome), (email), (tel), (senha) VALUES (?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, tel);
            stmt.setString(4, senha);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void excluir(int id) {
        PreparedStatement stmt = null;

        String sql = "DELETE FROM newsletter_spring WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Dados deletados com sucesso");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
