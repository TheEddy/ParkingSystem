package UsefulTools;

import SortingTools.BaseLastID;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Fedor on 04.09.2016 15:43.
 */
class BaseNumberFiller {
    public BaseNumberFiller(Integer count) throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ArrayList<String> numbers = new ArrayList<String>();
        String NUMBER_INSERT = "INSERT INTO carsystem VALUES (?,?,?)";
        String str = new NumberGenerator().str();
        Integer randomInt = new NumberGenerator().randomInt(3);
        String database = "carsystem";
        Integer i = new BaseLastID().BaseLastID(database);

        try {
            java.sql.PreparedStatement INSERT = databaseConnection.getConnection().prepareStatement(NUMBER_INSERT);
            i = i++;
            while (i != count) {
                if (!numbers.contains(str)) {
                    numbers.add(str);
                    INSERT.setInt(1, i);
                    INSERT.setString(2, str);
                    INSERT.setInt(3, randomInt);
                    INSERT.execute();
                    i++;
                }
            }
            System.out.println(Arrays.toString(numbers.toArray()) + "\n" + numbers.size());
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