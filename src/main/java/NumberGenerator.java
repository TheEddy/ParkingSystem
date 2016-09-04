import java.lang.*;
import java.util.Random;

/**
 * Created by Fedor on 04.09.2016 14:20 15:31.
 */
class NumberGenerator {

    NumberGenerator() {
        char[] chars = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};

        String str = new String(chars, randomInt(), 1) + randomInt() +
                randomInt() + randomInt() + new String(chars, randomInt(), 1)
                + new String(chars, randomInt(), 1) + region();

        System.out.println(str);
    }

    private int randomInt() {
        Random random = new Random();
        return random.nextInt(10);
    }

    private String region() {
        String[] regions = {"77", "97", "99", "177", "197", "199", "777", "50", "90", "150", "190", "750"};
        int number = randomInt();
        return regions[number];
    }
}
