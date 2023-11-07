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
}
