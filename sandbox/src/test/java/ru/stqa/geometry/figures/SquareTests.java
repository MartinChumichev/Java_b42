package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(25, new Square(5).area());
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(20, new Square(5).perimeter());
    }

    @Test
    void cannotCreateSquareWithNegativeSide() {
        try {
            new Square(-5);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
        // ОК
        }
    }

    @Test
    void testEquality() {
        var s1 = new Square(2.0);
        var s2 = new Square(2.0);
        Assertions.assertEquals(s1 ,s2);
    }

    @Test
    void testNotEquality() {
        var s1 = new Square(1.0);
        var s2 = new Square(2.0);
        Assertions.assertNotEquals(s1 ,s2);
    }

}
