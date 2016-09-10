package SortingTools;

import UsefulTools.DatabaseConnection;

import java.sql.*;
import java.util.Random;

/**
 * Created by Fedor on 05.09.2016 20:18.
 */
public class BaseImporter {

    public BaseImporter(String car_number, String database) throws SQLException{

        DatabaseConnection databaseConnection = new DatabaseConnection();
        String NUMBER_INSERT = "INSERT INTO carsystem VALUES (?,?,?)";

            BaseLastID baseLastID = new BaseLastID();
            Integer lastID = baseLastID.BaseLastID(database);
            Integer firstFreeID = lastID+1;
            PreparedStatement INSERT = databaseConnection.getConnection().prepareStatement(NUMBER_INSERT);
            Random random = new Random();
            Integer rand = random.nextInt(3);
            INSERT.setInt(1, firstFreeID);
            INSERT.setString(2,car_number);
            INSERT.setInt(3, rand);
            INSERT.execute();
            System.out.println(
                             "\nНомер " + car_number +" добавлен  в БД." +
                             "\nНомер добавлен с количеством инцидентов: " + rand + "\nПод ID: " + firstFreeID);
        }
}
