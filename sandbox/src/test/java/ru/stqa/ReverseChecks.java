package ru.stqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ReverseChecks {

    @Test
    void testSqrt() {
        var input = 5.0;
        var result = Math.sqrt(input);
        var reverse = result * result;
        Assertions.assertEquals(input, reverse, 0.00001);
    }

    @Test
    void testSort() {
        var list = new ArrayList<>(List.of(3, 0, 4 ,7));
        list.sort(Integer::compareTo);
        for (int i = 0; i < list.size() - 1; i++) {
            Assertions.assertTrue(list.get(i) <= list.get(i + 1));
        }
    }
}
