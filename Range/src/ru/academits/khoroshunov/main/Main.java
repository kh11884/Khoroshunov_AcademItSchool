package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(2, 8);
        System.out.println("Длина заданного диапазона: " + range.getLength());
        range.setFrom(1.2);
        range.setTo(15.2);
        System.out.println("Длина заданного диапазона: " + range.getLength());

        double x1 = 2.8;
        if (range.isInside(x1)) {
            System.out.println("Число - " + (x1) + " принадлежит заданному диапазону");
        } else {
            System.out.println("Число - " + (x1) + " находится за пределами заданного диапазона");
        }
        double x2 = 15.8;
        if (range.isInside(x2)) {
            System.out.println("Число - " + (x2) + " принадлежит заданному диапазону");
        } else {
            System.out.println("Число - " + (x2) + " находится за пределами заданного диапазона");
        }

        Range range1 = new Range(5, 10);
        Range range2 = new Range(10, 15);

        Range rangeOut = range1.getIntersection(range2);
        if (rangeOut == null) {
            System.out.println("Пересечение диапазонов. диапазоны не пересекаюся");
        } else {
            System.out.println("Пересечение диапазонов. Новый диапазон: " + rangeOut.getFrom() + " - " + rangeOut.getTo());
        }

        Range[] rangesUnion = range1.getUnion(range2);
        if (rangesUnion.length == 1) {
            System.out.println("Объединение диапазонов. Новый диапазон: " +
                    rangesUnion[0].getFrom() + " - " + rangesUnion[0].getTo());
        } else {
            System.out.println("Объединение диапазонов.  Новые диапазоны: " +
                    rangesUnion[0].getFrom() + " - " + rangesUnion[0].getTo() + " и " +
                    rangesUnion[1].getFrom() + " - " + rangesUnion[1].getTo());
        }

        Range[] rangesOdd = range1.getDifference(range2);
        if (rangesOdd.length == 0) {
            System.out.println("Разность диапазонов. Пустой диапазон.");
        } else if (rangesOdd.length == 1) {
            System.out.println("Разность диапазонов. Новый диапазон: " +
                    rangesOdd[0].getFrom() + " - " + rangesOdd[0].getTo());
        } else {
            System.out.println("Разность диапазонов.  Новые диапазоны: " +
                    rangesOdd[0].getFrom() + " - " + rangesOdd[0].getTo() + " и " +
                    rangesOdd[1].getFrom() + " - " + rangesOdd[1].getTo());
        }
    }
}