package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea(){
        Triangle triangle = new Triangle(5.5, 6.7, 7.8);
        Assertions.assertEquals(18.1, triangle.area(triangle));
    }

    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(9, new Triangle(2, 3, 4).perimeter());

    }
}
