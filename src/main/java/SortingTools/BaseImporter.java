package SortingTools;

import UsefulTools.DatabaseConnection;

import java.sql.*;
import java.util.Random;

/**
 * Created by Fedor on 05.09.2016 20:18.
 */
public class BaseImporter{

    public BaseImporter(){

    }

    private static String car_number;
    private static int slot_id;
    private static String database;

    public void run() throws SQLException{

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String NUMBER_INSERT = "INSERT INTO \"+ database +\" VALUES (?,?,?)";

        PreparedStatement INSERT = connection.prepareStatement(NUMBER_INSERT);
        Random random = new Random();
        Integer rand = random.nextInt(3);
        INSERT.setInt(1, slot_id);
        INSERT.setString(2, car_number);
        INSERT.setInt(3, rand);
        INSERT.execute();
        System.out.println(
                "\nНомер " + car_number +" добавлен  в БД." +
                        "\nНомер добавлен с количеством инцидентов: " + rand + "\nПод ID: " + slot_id);
        connection.close();
    }



    public void CarSystem(String car_number) throws SQLException {
        setDatabase("carsystem");
        setCar_number(car_number);
        BaseLastID baseLastID = new BaseLastID();
        Integer lastID = baseLastID.BaseLastID(database);
        Integer firstFreeID = lastID+1;
        setSlot_id(firstFreeID);
        run();
    }

    public String getCar_number(){
        return car_number;
    }

    public int getSlot_id(){
        return slot_id;
    }

    public String getDatabase(){
        return database;
    }

    public void setCar_number(String car_number){
        BaseImporter.car_number = car_number;
    }

    public void setSlot_id(int slot_id){
        BaseImporter.slot_id = slot_id;
    }

    public static void setDatabase(String database) {
        BaseImporter.database = database;
    }

}
