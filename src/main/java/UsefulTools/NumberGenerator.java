package UsefulTools;

import java.util.Random;

/**
 * Created by Fedor on 08.09.2016 19:06.
 */
public class NumberGenerator {
    public String str() {
        char[] chars = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
        return new String(chars, randomInt(12), 1) + randomInt(10) +
                randomInt(10) + randomInt(10) + new String(chars, randomInt(12), 1)
                + new String(chars, randomInt(12), 1) + region();
    }

    int randomInt(int n) {
        Random random = new Random();
        return random.nextInt(n);
    }

    private String region() {
        String[] regions = {"77", "97", "99", "177", "197", "199", "777", "50", "90", "150", "190", "750"};
        int number = randomInt(12);
        return regions[number];
    }
}
