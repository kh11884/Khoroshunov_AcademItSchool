package ru.academits.khoroshunov.shapes;

public class Rectangle implements Shapes {
    private double width;
    private double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return (width + height) * 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 &&
                Double.compare(rectangle.height, height) == 0;
    }

    @Override
    public int hashCode() {
        Object o1 = width;
        Object o2 = height;
        int result = 1;
        result = 31 * result + o1.hashCode();
        result = 31 * result + o2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Прямоугольник. " + "ширина = " + getWidth() + "высота = " + getHeight();
    }
}