package ru.academits.khoroshunov.range;

class Range {
    private double from;
    private double to;

    Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    double getFrom() {
        return this.from;
    }

    double getTo() {
        return this.to;
    }

    double getLength() {
        return this.to - this.from;
    }

    boolean isInside(double x) {
        return x >= this.from && x <= this.to;
    }

    Range getIntersectionRange(Range r) {
        if (this.from <= r.from && this.to >= r.to) {
            return r;
        }
        if (this.from >= r.from && this.to <= r.to) {
            return this;
        }
        if (this.from <= r.from && this.to <= r.to && this.to >= r.from) {
            return new Range(r.from, this.to);
        }
        if (this.from >= r.from && this.to >= r.to && this.from <= r.to) {
            return new Range(this.from, r.to);
        }
        return null;
    }

    Range[] getUnionRanges(Range r) {
        Range[] ranges = new Range[2];
        if (this.from >= r.from && this.to <= r.to) {
            ranges[0] = r;
        }
        if (this.from <= r.from && this.to >= r.to) {
            ranges[0] = this;
        }
        if (this.to <= r.from || r.to <= this.from) {
            if (this.to <= r.from) {
                ranges[0] = this;
                ranges[1] = r;
            } else {
                ranges[0] = r;
                ranges[1] = this;
            }
        }
        if (this.from <= r.from && this.to <= r.to && this.to >= r.from) {
            ranges[0] = new Range(this.from, r.to);
        }
        if (this.from >= r.from && this.to >= r.to && this.from <= r.to) {
            ranges[0] = new Range(r.from, this.to);
        }
        return ranges;
    }

    Range[] getOddRanges(Range r) {
        Range[] ranges = new Range[2];
        if (this.from >= r.from && this.to <= r.to) {
            return ranges;
        }
        if (this.to < r.from || this.from > r.to) {
            ranges[0] = this;
        }
        if (this.from < r.from && this.to <= r.to && this.to >= r.from) {
            ranges[0] = new Range(this.from, r.from);
        }
        if (this.from >= r.from && this.to > r.to && this.from <= r.to) {
            ranges[0] = new Range(r.to, this.to);
        }
        if (this.from < r.from && this.to > r.to) {
            ranges[0] = new Range(this.from, r.from);
            ranges[1] = new Range(r.to, this.to);
        }
        return ranges;
    }
}

public class Main {
    public static void main(String[] args) {
        Range range = new Range(1.2, 15.2);
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

        Range range1 = new Range(1, 22);
        Range range2 = new Range(5, 14);

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

