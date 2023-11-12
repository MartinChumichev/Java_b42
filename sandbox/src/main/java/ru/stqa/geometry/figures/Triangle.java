package ru.stqa.geometry.figures;

public class Triangle {

    private double side1;
    private double side2;
    private double side3;


    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Сторона треугольника должна быть положительной");
        }
        if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1) {
            throw new IllegalArgumentException("Такого треугольника не существует");
        }
    }

    public double perimeter() {
        return this.side1 + this.side2 + this.side3;
    }

    public double area() {
        double halfPerimeter = perimeter() / 2;
        return (double) Math.round(Math.sqrt(halfPerimeter
               * (halfPerimeter - this.side1)
               * (halfPerimeter - this.side2)
               * (halfPerimeter - this.side3)) * 10) / 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        return ((Double.compare(triangle.side3, this.side3) == 0 || Double.compare(triangle.side3, this.side2) == 0 || Double.compare(triangle.side3, this.side1) == 0)
               && (Double.compare(triangle.side2, this.side3) == 0 || Double.compare(triangle.side2, this.side2) == 0 || Double.compare(triangle.side2, this.side1) == 0)
               && (Double.compare(triangle.side1, this.side3) == 0 || Double.compare(triangle.side1, this.side2) == 0 || Double.compare(triangle.side1, this.side1) == 0));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
