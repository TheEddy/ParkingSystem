import SortingTools.BaseFinder;
import SortingTools.BaseImporter;
import SortingTools.ParkImporter;
import UsefulTools.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Fedor on 19-Oct-16.
 */

public class BaseStatus {

    private static ArrayList baseStatus;

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
            Integer slot_id;
            Integer slot_status;
            String string_status;
            ArrayList arr = new ArrayList();
            //arr.add("8");

            while(rs.next()) {
                //slot_id = rs.getInt(1);
                string_status = rs.getString(2);
                //System.out.println(string_status); - Для отображения статусов.
                if (string_status.equals("Свободен")) slot_status = 1; //Запись в массив значений(свободен\занят)
                else slot_status = 0;
                arr.add(slot_status);
            }
            setBaseStatus(arr); //отправка созданого массива в глобальную переменную.
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

    public static void baseStatusInsert (Integer slot_id, String car_number){
        baseStatus.set(slot_id, 1);
        BaseImporter baseImporter = new BaseImporter();
        try {
            boolean baseFinder = new BaseFinder().BaseFinder(car_number); //Поиск по базе данных(номеров)

            if(!baseFinder){ //Если нет подобной записи, то создать
                baseImporter.CarSystem(car_number);
                baseImporter.run();
            }

            new ParkImporter(car_number, slot_id); //Запись нового статуса парк.места

        } catch (SQLException e) {
            System.err.println("Не удалось произвести поиск по базе.");
        }
    }


    public static ArrayList getBaseStatus() {
        return baseStatus;
    }

    public static void setBaseStatus(ArrayList baseStatus) {
        BaseStatus.baseStatus = baseStatus;
    }
}
