import java.sql.*;

/**
 * Created by Fedor on 03.09.2016 14:29.
 */
public class core {
    public static void main(String... args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        String query = "SELECT * FROM parkingsystem WHERE parkingsystem.slot_status = 'Свободен'";
        NumberGenerator car_number = new NumberGenerator();
        System.out.println();

        try {
            Statement statement = databaseConnection.getConnection().createStatement();
            ResultSet id = statement.executeQuery(query);

            while (id.next()) {
                int slot_id = id.getInt("slot_id");
                System.out.println(slot_id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
