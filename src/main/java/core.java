import UsefulTools.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fedor on 03.09.2016 14:29.
 */
public class core {
    public static void main(String... args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        String car123 = "'У924МС90'";
        String SELECT_NUMBER = "SELECT carsystem.* from carsystem WHERE MATCH(car_number)" +
                               " AGAINST("+ car123 +" IN NATURAL LANGUAGE MODE)";
        String NUMBER_INSERT = "INSERT INTO parkingsystem VALUES (?,?,?,?)";
        Statement stmt;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_NUMBER);
            PreparedStatement SELECT = databaseConnection.getConnection().prepareStatement(SELECT_NUMBER);
            Integer car_id;
            String car_numbers;
            Integer car_accident;

            rs.next();
            car_id = rs.getInt(1);
            car_numbers = rs.getString(2);
            car_accident = rs.getInt(3);
            arrayList2.add(car_accident);
            arrayList1.add(car_id);
            arrayList.add(car_numbers);

            System.out.println(
                    "ID автомобиля в БД:   "   + Arrays.toString(arrayList1.toArray())+
                    "\nГос. Номер автомобиля:" + Arrays.toString(arrayList.toArray())+
                    "\nКоличество ДТП:       " + Arrays.toString(arrayList2.toArray()));

            //System.out.println(Arrays.toString(arrayList.toArray()) + "\n" + arrayList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                databaseConnection.getConnection().close();
                if (databaseConnection.getConnection().isClosed()) {
                    System.out.println("Соединение с БД закрыто");
                }
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть соединение.");
            }
        }
    }
}
