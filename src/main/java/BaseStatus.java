import SortingTools.BaseFinder;
import SortingTools.BaseImporter;
import UsefulTools.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fedor on 19-Oct-16.
 */

public class BaseStatus {

    private static ArrayList baseStatus;
    private static boolean contains;

    public BaseStatus() {
        baseStatusUpdate();
        ArrayList arr = getBaseStatus();
        if(arr == null) System.out.println("База пуста");
    }


    public static void baseStatusUpdate() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String SELECT_NUMBER = "SELECT parkingsystem.* FROM parkingsystem";

        try {
            Statement statement;
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_NUMBER);
            //Integer slot_id;
            Integer slot_status;
            String string_status;
            ArrayList arr = new ArrayList();
            arr.add("8");

            while(rs.next()) {
                //slot_id = rs.getInt(1);
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
                    //System.out.println("Соединение с БД закрыто");
                }
            } catch (SQLException e) {
                System.err.println("Не удалось завершить соединение");
            }
        }
    }

    private static void baseStatusInsert (Integer slot_id, String car_number){
        baseStatus.set(slot_id, 1);
        try {
            boolean baseFinder = new BaseFinder().BaseFinder(car_number);
            contains = setContains(baseFinder);
            if(!getContains()){
                new BaseImporter(car_number, "carsystem");
            }
        } catch (SQLException e) {
            System.err.println("Не удалось произвести поиск по базе.");
        }
    }


    private static boolean getContains() {
        return contains;
    }

    private static boolean setContains(boolean contains) {
        BaseStatus.contains = contains;
        return contains;
    }

    public static ArrayList getBaseStatus() {
        return baseStatus;
    }

    public static void setBaseStatus(ArrayList baseStatus) {
        BaseStatus.baseStatus = baseStatus;
    }
}
