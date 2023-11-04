package ru.stqa.geometry.figures;

public class Rectangle {

    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public static void printRectangleArea(Rectangle rectangle) {
        String text = String.format("Площадь прямоугольника со сторонами %f и %f равна %f",
               rectangle.side1, rectangle.side2, rectangle.area());
        System.out.println(text);
    }

    public double area() {
        return this.side1 * this.side2;
    }

    public double perimeter() {
        return 2 * (this.side1 * this.side2);
    }

}
