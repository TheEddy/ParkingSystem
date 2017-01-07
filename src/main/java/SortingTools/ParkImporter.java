package SortingTools;

import UsefulTools.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by Fedor on 07.01.2017 16:47.
 */
public class ParkImporter extends BaseImporter {
    public ParkImporter(String car_number, int slot_id) throws SQLException{
        setDatabase("parkingsystem");
        setCar_number(car_number);
        setSlot_id(slot_id);
        run();
    }

    @Override
    public void run() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        Date nowtime = new Date();
        Time time = new java.sql.Time(nowtime.getTime());
        LocalTime localtime = time.toLocalTime();
        localtime = localtime.plusMinutes(30);
        time = Time.valueOf(localtime);
        String database = getDatabase();
        String NUMBER_INSERT = "INSERT INTO \"+ database +\" VALUES (?,?,?,?)";
        PreparedStatement INSERT = connection.prepareStatement(NUMBER_INSERT);
        INSERT.setInt(1, getSlot_id());
        INSERT.setString(2, "Зарезервирован");
        INSERT.setString(3, getCar_number());
        INSERT.setTime(4, time);
        INSERT.execute();
        System.out.println(
                "\nНомер " + getCar_number() +" добавлен  в БД." + "\nПод ID: " + getSlot_id());
        connection.close();
    }
}
