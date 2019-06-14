package ru.academits.khoroshunov.shapes;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shapes> {
    @Override
    public int compare(Shapes o1, Shapes o2) {
        return Double.compare(o2.getArea(),o1.getArea());
    }
}
