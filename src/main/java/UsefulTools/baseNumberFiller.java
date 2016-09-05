package UsefulTools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Fedor on 04.09.2016 15:43.
 */
class baseNumberFiller {
    baseNumberFiller() {

        //System.out.println(str);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ArrayList<String> numbers = new ArrayList<String>();
        String NUMBER_INSERT = "INSERT INTO carsystem VALUES (?,?,?)";


        try {
            java.sql.PreparedStatement INSERT = databaseConnection.getConnection().prepareStatement(NUMBER_INSERT);
            int i = 1;
            while(i != 10001) {
                if (!numbers.contains(str())) {
                    numbers.add(str());
                    INSERT.setInt(1,i);
                    INSERT.setString(2,str());
                    INSERT.setInt(3, randomInt(3));
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

    private String str() {
        char[] chars = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
        return new String(chars, randomInt(12), 1) + randomInt(10) +
                randomInt(10) + randomInt(10) + new String(chars, randomInt(12), 1)
                + new String(chars, randomInt(12), 1) + region();
    }

    private int randomInt(int n) {
        Random random = new Random();
        return random.nextInt(n);
    }

    private String region() {
        String[] regions = {"77", "97", "99", "177", "197", "199", "777", "50", "90", "150", "190", "750"};
        int number = randomInt(12);
        return regions[number];
    }
}