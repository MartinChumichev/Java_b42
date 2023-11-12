package ru.stqa.geometry.figures;

public class Rectangle {

    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
        if (side1 < 0 || side2 < 0) {
            throw new IllegalArgumentException("Сторона прямоугольника должны бть положительной");
        }
    }

    public static void printRectangleArea(Rectangle rectangle) {
        System.out.printf("Площадь прямоугольника со сторонами %f и %f равна %f",
               rectangle.side1, rectangle.side2, rectangle.area());
    }

    public double area() {
        return this.side1 * this.side2;
    }

    public double perimeter() {
        return 2 * (this.side1 * this.side2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        return (Double.compare(rectangle.side2, this.side2) == 0 && Double.compare(rectangle.side1, this.side1) == 0)
               || (Double.compare(rectangle.side1, this.side2) == 0 && Double.compare(rectangle.side2, this.side1) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
