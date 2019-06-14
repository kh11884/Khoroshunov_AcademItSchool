package ru.academits.khoroshunov.shapes;

public class Main {
    public static void main(String[] args) {
        Shapes shape1 = new Square(4);
        System.out.println("Квадрат.");
        System.out.println("высота: " + shape1.getWidth());
        System.out.println("ширина: " + shape1.getHeight());
        System.out.println("площадь: " + shape1.getArea());
        System.out.println("периметр: " + shape1.getPerimeter());
        System.out.println();

        Shapes shape2 = new Triangle(1, 1, -2, 4, -2, -2);
        System.out.println("Треугольник.");
        System.out.println("высота: " + shape2.getWidth());
        System.out.println("ширина: " + shape2.getHeight());
        System.out.println("площадь: " + shape2.getArea());
        System.out.println("периметр: " + shape2.getPerimeter());
        System.out.println();

        Shapes shape3 = new Circle(2);
        System.out.println("Круг.");
        System.out.println("высота: " + shape3.getWidth());
        System.out.println("ширина: " + shape3.getHeight());
        System.out.println("площадь: " + shape3.getArea());
        System.out.println("периметр: " + shape3.getPerimeter());
    }
}
