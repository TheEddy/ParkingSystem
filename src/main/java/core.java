import SortingTools.BaseFinder;
import SortingTools.BaseImporter;
import SortingTools.BaseLastID;

import java.sql.*;


/**
 * Created by Fedor on 03.09.2016 14:29.
 */
public class core {
    public static void main(String... args) {
        String database = "carsystem";
        String car_number = "А847ВА777";

        try {
            boolean baseFinder = new BaseFinder().BaseFinder(car_number);
            if (baseFinder) {
                System.out.println("Этот номер найден в ДБ");
            }
            else {
                //Integer lastId = new BaseLastID().BaseLastID(database);
                BaseImporter baseImporter = new BaseImporter(car_number, database);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка в подключении к БД");
        }
    }
}