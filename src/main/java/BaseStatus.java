import UsefulTools.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fedor on 19-Oct-16.
 */

public class BaseStatus {

    private static ArrayList baseStatus;

    public static void main(String... args) {
        baseStatusUpdate();
        ArrayList arr = getBaseStatus();
        if(arr != null) System.out.println(Arrays.toString(arr.toArray()));
        else System.out.println("База пуста");
        String test_status = arr.get(0).toString();
        String l = "CL";
        arr.set(0, l);
        System.out.println(test_status);
        System.out.println(Arrays.toString(arr.toArray()));
    }


    private static void baseStatusUpdate() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String SELECT_NUMBER = "SELECT parkingsystem.* FROM parkingsystem";

        try {
            Statement statement;
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_NUMBER);
            Integer slot_id;
            Integer slot_status;
            String string_status;
            ArrayList arr = new ArrayList();
            arr.add("ControlLine");

            while(rs.next()) {
                slot_id = rs.getInt(1);
                string_status = rs.getString(2);
                //System.out.println(string_status); - Для отображения статусов.
                if (string_status.equals("Свободен")) slot_status = 1;
                else slot_status = 0;
                arr.add(slot_status);
                setBaseStatus(arr);
            }
        }
        catch (SQLException e) {
            System.err.println("Не удалось обновить статус.");
        }
        finally {
            try {
                connection.close();
                if (connection.isClosed()) {
                    System.out.println("Соединение с БД закрыто");
                }
            } catch (SQLException e) {
                System.err.println("Не удалось завершить соединение");
            }
        }
    }

    public static ArrayList getBaseStatus() {
        return baseStatus;
    }

    public static void setBaseStatus(ArrayList baseStatus) {
        BaseStatus.baseStatus = baseStatus;
    }
}
