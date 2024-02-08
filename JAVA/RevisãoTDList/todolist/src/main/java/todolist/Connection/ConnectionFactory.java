package todolist.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
private static final String URL = "jdbc:postgresql://localhost:5432/postgresql";
private static final String USUARIO = "localhost";
private static final String SENHA = "postgres7210";
public static Connection getConnection() {
try {
return DriverManager.getConnection(URL, USUARIO, SENHA);
} catch (SQLException e) {
throw new RuntimeException("Erro ao obter conexão com o banco de dados.", e);
}
}
public static void closeConnection(Connection connection) {
try {
if (connection != null) {
connection.close();

}
} catch (SQLException ex) {
ex.printStackTrace();
}
}
}