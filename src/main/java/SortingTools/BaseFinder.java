package SortingTools;

import UsefulTools.DatabaseConnection;

import java.sql.*;

/**
 * Created by Fedor on 05-Sep-16.
 */
public class BaseFinder {
    public static void main(String[] args){
        String database = "carsystem";
        String car_number = "В887МХ177";

    }

    public boolean BaseFinder(String car_number) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String search = "\"\'" + car_number + "\'\"";
        String SELECT_NUMBER = "SELECT carsystem.* from carsystem WHERE MATCH(car_number)" +
                " AGAINST("+ search +" IN NATURAL LANGUAGE MODE)";
        Statement statement;
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_NUMBER);
        try {
            rs.next();
            /*Integer car_id;
            String car_numbers;
            Integer car_accident;
            car_id = rs.getInt(1);
            car_numbers = rs.getString(2);
            car_accident = rs.getInt(3);
            System.out.println(
                    "ID автомобиля в БД:   " +  car_id +
                            "\nГос. Номер автомобиля:" + car_numbers +
                            "\nКоличество ДТП:       " + car_accident);*/
            return true;
        }
        catch (SQLException e) {
            return false;
        }
        /*if (rs.next()) {

            //Записываем значения из БД в переменные
            rs.next(); // Выбирает найденое значение.



        }*/

        //return false;
    }

}
