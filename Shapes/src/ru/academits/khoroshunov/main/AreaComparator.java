package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.shapes.Shape;
import java.util.Comparator;

class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getArea(), shape1.getArea());
    }
}