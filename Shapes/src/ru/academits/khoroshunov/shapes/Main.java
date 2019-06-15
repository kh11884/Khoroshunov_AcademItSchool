package ru.academits.khoroshunov.shapes;

import java.util.Arrays;

public class Main {
    private static Shapes getMaxAreaShape(Shapes[] array) {
        AreaComparator areaComparator = new AreaComparator();
        Arrays.sort(array, areaComparator);
        return array[0];
    }

    private static Shapes getSecondAreaShape(Shapes[] array) {
        AreaComparator areaComparator = new AreaComparator();
        Arrays.sort(array, areaComparator);
        return array[1];
    }

    public static void main(String[] args) {
        Shapes[] shapes = {
                new Square(4),
                new Circle(2),
                new Triangle(1, 1, 3, 3, 5, 1),
                new Rectangle(4, 2),
                new Square(5),
                new Circle(3),
                new Triangle(2, 2, 4, 4, 6, 2),
                new Rectangle(1, 7),
                new Square(4.5),
                new Circle(2.5)
        };

        Shapes shape1 = getMaxAreaShape(shapes);
        System.out.println("Фигура с самой большой площадью: " + shape1 + ". Площадь = " + shape1.getArea());

        Shapes shape2 = getSecondAreaShape(shapes);
        System.out.println("Фигура с второй по величина площадью: " + shape2 + ". Площадь = " + shape2.getArea());
    }
}
