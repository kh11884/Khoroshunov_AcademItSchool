package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.shapes.*;

import java.util.Arrays;

public class Main {
    private static Shape getMaxAreaShape(Shape[] shapes) {
        AreaComparator areaComparator = new AreaComparator();
        Arrays.sort(shapes, areaComparator);
        return shapes[0];
    }

    private static Shape getSecondPerimeterShape(Shape[] shapes) {
        PerimeterComparator perimeterComparator = new PerimeterComparator();
        Arrays.sort(shapes, perimeterComparator);
        return shapes[1];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(4),
                new Circle(2),
                new Triangle(1, 1, 3, 3, 5, 1),
                new Rectangle(4, 2),
                new Square(5),
                new Circle(13),
                new Triangle(1, 1, 3, 3, 5, 1),
                new Rectangle(1, 7),
                new Square(4.5),
                new Circle(2.0)
        };

        Shape shape1 = getMaxAreaShape(shapes);
        System.out.println("Фигура с самой большой площадью: " + shape1 + ". Площадь = " + shape1.getArea());

        Shape shape2 = getSecondPerimeterShape(shapes);
        System.out.println("Фигура с вторым по величине периметром: " + shape2 + ". Периметр = " + shape2.getPerimeter());
    }
}
