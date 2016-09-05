import SortingTools.BaseFinder;
import SortingTools.BaseImporter;
import SortingTools.BaseLastID;
import UsefulTools.NumberGenerator;

import java.sql.*;


/**
 * Created by Fedor on 03.09.2016 14:29.
 */
public class core {
    public static void main(String... args) {
        String database = "carsystem";
        String car_number = new NumberGenerator().str();
        try {
            BaseFinder baseFinder = new BaseFinder(car_number);
            Integer lastId = new BaseLastID().BaseLastID(database);
            BaseImporter baseImporter = new BaseImporter(car_number, database);
        } catch (SQLException e) {
            System.err.println("Невозможно добавить данный номер в ДБ");
        }
    }
}