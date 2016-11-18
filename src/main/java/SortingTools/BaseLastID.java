package SortingTools;

import UsefulTools.DatabaseConnection;

import java.sql.*;

/**
 * Created by Fedor on 05.09.2016 21:26.
 */
public class BaseLastID {
    public Integer BaseLastID(String database) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        Statement st = null;
            st = connection.createStatement();
            int lastLine = 0;
            ResultSet res = st.executeQuery("SELECT * FROM  "+ database +" where carsystem.car_id > " + lastLine);
            while (res.next()) {
                lastLine = res.getInt("car_id");
            }
            connection.close();
            return lastLine;
    }
}
