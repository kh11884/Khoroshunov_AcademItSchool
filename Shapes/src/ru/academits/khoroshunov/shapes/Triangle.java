package ru.academits.khoroshunov.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private static double getMaxValue(double a, double b, double c) {
        if (a < b) {
            a = b;
        }
        if (a < c) {
            a = c;
        }
        return a;
    }

    private static double getMinValue(double a, double b, double c) {
        if (a > b) {
            a = b;
        }
        if (a > c) {
            a = c;
        }
        return a;
    }

    private static double getSideLength(double point1X, double point1Y, double point2X, double point2Y) {
        return Math.sqrt(Math.pow(point2X - point1X, 2) + Math.pow(point2Y - point1Y, 2));
    }

    @Override
    public double getWidth() {
        return getMaxValue(x1, x2, x3) - getMinValue(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getMaxValue(y1, y2, y3) - getMinValue(y1, y2, y3);
    }

    @Override
    public double getArea() {
        return Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) / 2;
    }

    @Override
    public double getPerimeter() {
        double side1 = getSideLength(x1, y1, x2, y2);
        double side2 = getSideLength(x2, y2, x3, y3);
        double side3 = getSideLength(x3, y3, x1, y1);
        return side1 + side2 + side3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.x1, x1) == 0 &&
                Double.compare(triangle.y1, y1) == 0 &&
                Double.compare(triangle.x2, x2) == 0 &&
                Double.compare(triangle.y2, y2) == 0 &&
                Double.compare(triangle.x3, x3) == 0 &&
                Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Double.hashCode(x1);
        result = 31 * result + Double.hashCode(y1);
        result = 31 * result + Double.hashCode(x2);
        result = 31 * result + Double.hashCode(y2);
        result = 31 * result + Double.hashCode(x3);
        result = 31 * result + Double.hashCode(y3);
        return result;
    }

    @Override
    public String toString() {
        return "Треугольник. Вершина1 (" + x1 + ", " + y1 +
                "), Вершина2 (" + x2 + ", " + y2 +
                "), Вершина3 (" + x3 + ", " + y3 + ")";
    }
}
