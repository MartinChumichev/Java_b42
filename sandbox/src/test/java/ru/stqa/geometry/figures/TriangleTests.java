package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(18.1, new Triangle(5.5, 6.7, 7.8).area());
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(9, new Triangle(2, 3, 4).perimeter());

    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(1, -2, 3);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            //
        }
    }

    @Test
    void cannotCreateTriangleThatDoesNotExist() {
        try {
            new Triangle(1, 2, 4);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            //
        }
    }

    @Test
    void testEquality() {
        var t1 = new Triangle(1, 2, 3);
        var t2 = new Triangle(1, 2, 3);
        Assertions.assertEquals(t1 ,t2);
    }

    @Test
    void testNotEquality() {
        var t1 = new Triangle(1, 2, 3);
        var t2 = new Triangle(2, 3, 4);
        Assertions.assertNotEquals(t1, t2);
    }

    @Test
    void testPass() {
        var t1 = new Triangle(1, 2, 3);
        var t2 = new Triangle(3, 1, 2);
        Assertions.assertEquals(t1, t2);
    }
}
