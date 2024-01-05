package common;

import java.util.Random;

public class CommonFunctions {
    public static String randomString(int a) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a; i++) {
            result.append((char) ('a' + new Random().nextInt(26)));
        }
        return result.toString();
    }
}
