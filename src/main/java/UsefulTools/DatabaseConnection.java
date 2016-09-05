package UsefulTools;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Fedor on 04.09.2016 11:13 15:31.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://194.87.234.46:3306/parkingsystem";
    private static final String USER = "parkingdata";
    private static final String PASSWORD = "KBwfFg";

    private Connection connection;

    public DatabaseConnection() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
        }

        catch (SQLException e) {
            System.err.println("Не удалось инициализировать драйвер");

        }

        finally {
            try {
                if (connection.isClosed()) {
                    System.out.println("Соединение с БД закрыто");
                }
            } catch (SQLException e) {
                System.err.println("Не удалось завершить соединение");
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

