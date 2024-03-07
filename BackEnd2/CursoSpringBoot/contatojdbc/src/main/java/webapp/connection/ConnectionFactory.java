package webapp.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    // Informações de conexão com o banco de dados
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "postgres7210";
    

    // Método para obter uma conexão com o banco de dados
   public static Connection getConnection(){
    try{
        return DriverManager.getConnection(url, user, password);        
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao obter conexão com o banco de dados.", e); // e = erro
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
   //Método para fechar a conexão e o objeto PreparedStatement
   public static void closeConnection(Connection connection, PreparedStatement stmt) {
   closeConnection(connection);
   try {
    if (stmt != null) {
        stmt.close();
    }
} catch (SQLException ex){
    ex.printStackTrace();
}
   }



    }

