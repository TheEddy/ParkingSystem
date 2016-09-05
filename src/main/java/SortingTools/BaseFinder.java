package SortingTools;

import UsefulTools.DatabaseConnection;

import java.sql.*;

/**
 * Created by Fedor on 05-Sep-16.
 */
public class BaseFinder {

    public static void main(String... args) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String search = "test";
        search = "\"\'" + search + "\'\"";
        String SELECT_NUMBER = "SELECT carsystem.* from carsystem WHERE MATCH(car_number)" +
                " AGAINST("+ search +" IN NATURAL LANGUAGE MODE)";
        Statement statement;
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_NUMBER);
        PreparedStatement SELECT = databaseConnection.getConnection().prepareStatement(SELECT_NUMBER);
        if (rs.next()) {
            Integer car_id;
            String car_numbers;
            Integer car_accident;
            //Записываем значения из БД в переменные
            rs.next(); // Выбирает найденое значение.
            car_id = rs.getInt(1);
            car_numbers = rs.getString(2);
            car_accident = rs.getInt(3);
            System.out.println(
                    "ID автомобиля в БД:   " +  car_id+
                            "\nГос. Номер автомобиля:" + car_numbers+
                            "\nКоличество ДТП:       " + car_accident);
            //throw SQLOutput.
        }
        //else throw SQL

    }

}
