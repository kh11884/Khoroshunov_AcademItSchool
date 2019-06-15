package ru.academits.khoroshunov.shapes;

public class Triangle implements Shapes {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private double max(double a, double b, double c) {
        if (a < b) {
            a = b;
        }
        if (a < c) {
            a = c;
        }
        return a;
    }

    private double min(double a, double b, double c) {
        if (a > b) {
            a = b;
        }
        if (a > c) {
            a = c;
        }
        return a;
    }

    private double sideLength(double a1, double b1, double a2, double b2) {
        return Math.sqrt(Math.pow(a2 - a1, 2) + Math.pow(b2 - b1, 2));
    }

    @Override
    public double getWidth() {
        return max(x1, x2, x3) - min(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return max(y1, y2, y3) - min(y1, y2, y3);
    }

    @Override
    public double getArea() {
        return Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) / 2;
    }

    @Override
    public double getPerimeter() {
        double side1 = sideLength(x1, y1, x2, y2);
        double side2 = sideLength(x2, y2, x3, y3);
        double side3 = sideLength(x3, y3, x1, y1);
        return side1 + side2 + side3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.getArea(), getArea()) == 0 &&
                Double.compare(triangle.getPerimeter(), getPerimeter()) == 0;
    }

    @Override
    public int hashCode() {
        Object o1 = getArea();
        Object o2 = getPerimeter();
        int result = 1;
        result = 31 * result + o1.hashCode();
        result = 31 * result + o2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Треугольник. " + "Вершина1 (" + x1 + ", " + y1 + "), " +
                "Вершина2 (" + x2 + ", " + y2 + "), " +
                "Вершина3 (" + x3 + ", " + y3 + ")";
    }
}
