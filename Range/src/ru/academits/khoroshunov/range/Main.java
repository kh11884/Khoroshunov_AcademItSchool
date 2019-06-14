package ru.academits.khoroshunov.range;

class Range {
    private double from;
    private double to;

    Range(double from, double to) {
        if (from < to) {
            this.from = from;
            this.to = to;
        } else {
            this.from = to;
            this.to = from;
        }
    }

    double getFrom() {
        return from;
    }

    double getTo() {
        return to;
    }

    double getLength() {
        return to - from;
    }

    boolean isInside(double x) {
        return x >= from && x <= to;
    }

    Range getIntersectionRange(Range r) {
        if (from <= r.from && to >= r.to) {
            return r;
        }
        if (from >= r.from && to <= r.to) {
            return this;
        }
        if (from <= r.from && to <= r.to && to >= r.from) {
            return new Range(r.from, to);
        }
        if (from >= r.from && to >= r.to && from <= r.to) {
            return new Range(from, r.to);
        }
        return null;
    }

    Range[] getUnionRanges(Range r) {
        Range[] ranges = new Range[2];
        if (from >= r.from && to <= r.to) {
            ranges[0] = r;
        }
        if (from <= r.from && to >= r.to) {
            ranges[0] = this;
        }
        if (to <= r.from || r.to <= from) {
            if (to <= r.from) {
                ranges[0] = this;
                ranges[1] = r;
            } else {
                ranges[0] = r;
                ranges[1] = this;
            }
        }
        if (from <= r.from && to <= r.to && to >= r.from) {
            ranges[0] = new Range(from, r.to);
        }
        if (from >= r.from && to >= r.to && from <= r.to) {
            ranges[0] = new Range(r.from, to);
        }
        return ranges;
    }

    Range[] getOddRanges(Range r) {
        Range[] ranges = new Range[2];
        if (from >= r.from && to <= r.to) {
            return ranges;
        }
        if (to < r.from || from > r.to) {
            ranges[0] = this;
        }
        if (from < r.from && to <= r.to && to >= r.from) {
            ranges[0] = new Range(from, r.from);
        }
        if (from >= r.from && to > r.to && from <= r.to) {
            ranges[0] = new Range(r.to, to);
        }
        if (from < r.from && to > r.to) {
            ranges[0] = new Range(from, r.from);
            ranges[1] = new Range(r.to, to);
        }
        return ranges;
    }
}

public class Main {
    public static void main(String[] args) {
        Range range = new Range(15.2, 1.2);
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

        Range range1 = new Range(1, 14);
        Range range2 = new Range(12, 18);

        Range rangeOut = range1.getIntersectionRange(range2);
        if (rangeOut == null) {
            System.out.println("Пересечение диапазонов. диапазоны не пересекаюся");
        } else {
            System.out.println("Пересечение диапазонов. Новый диапазон: " + rangeOut.getFrom() + " - " + rangeOut.getTo());
        }

        Range[] rangesUnion = range1.getUnionRanges(range2);
        if (rangesUnion[1] == null) {
            System.out.println("Объединение диапазонов. Новый диапазон: " +
                    rangesUnion[0].getFrom() + " - " + rangesUnion[0].getTo());
        } else {
            System.out.println("Объединение диапазонов.  Новые диапазоны: " +
                    rangesUnion[0].getFrom() + " - " + rangesUnion[0].getTo() + " и " +
                    rangesUnion[1].getFrom() + " - " + rangesUnion[1].getTo());
        }

        Range[] rangesOdd = range1.getOddRanges(range2);
        if (rangesOdd[0] == null) {
            System.out.println("Разность диапазонов. Пустой диапазон.");
        } else if (rangesOdd[1] == null) {
            System.out.println("Разность диапазонов. Новый диапазон: " +
                    rangesOdd[0].getFrom() + " - " + rangesOdd[0].getTo());
        } else {
            System.out.println("Разность диапазонов.  Новые диапазоны: " +
                    rangesOdd[0].getFrom() + " - " + rangesOdd[0].getTo() + " и " +
                    rangesOdd[1].getFrom() + " - " + rangesOdd[1].getTo());
        }
    }
}

