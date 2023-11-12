package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(15, new Rectangle(3, 5).area());
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(16, new Rectangle(3, 5).perimeter());
    }

    @Test
    void cannotCreateRectangleWithNegativeSide() {
        try {
            new Rectangle(-5 , 2);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            // ОК
        }
    }
}
