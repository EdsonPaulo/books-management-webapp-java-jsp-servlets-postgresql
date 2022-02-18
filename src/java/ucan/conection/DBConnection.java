package ucan.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author edsonpaulo
 */
public class DBConnection {

    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/biblioteca_ucan";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "YOUR_DB_PASSWORD_HERE";

    private Connection connection = null;

    public DBConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro ao estabelecer conexão com a BD!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
