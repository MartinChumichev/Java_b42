package common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int a) {
        Supplier<Integer> randomInt = () -> new Random().nextInt(26);
        return Stream.generate(randomInt)
               .map(i -> 'a' + i)
               .map(Character::toString)
               .limit(a)
               .collect(Collectors.joining());
    }
}
