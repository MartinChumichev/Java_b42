package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {

        Stream<Square> squares = Stream.of(new Square(10), new Square(8), new Square(6));
        squares.forEach(Square::printSquareArea);

        Supplier<Square> generator = () -> new Square(new Random().nextDouble(10.0));
        Stream<Square> squares2 = Stream.generate(generator).limit(5);
        squares2.peek(Square::printSquareArea).forEach(Square::area);

        Rectangle.printRectangleArea(new Rectangle(3.0, 5.0));
    }

}
