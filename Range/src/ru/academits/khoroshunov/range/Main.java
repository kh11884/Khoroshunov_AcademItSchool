package ru.academits.khoroshunov.range;

class Range {
    private double from;
    private double to;

    Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    double getLength() {
        return this.to - this.from;
    }

    boolean isInside(double x) {
        return x >= this.from && x <= this.to;
    }
}

public class Main {
    public static void main(String[] args) {
        Range range = new Range(1.2, 15.2);
        System.out.println("Длина заданного диапазона: " + range.getLength());

        double x = 2.8;

        for (int i = 0; i < 30; i += 10) {
            if (range.isInside(x + i)) {
                System.out.println("Число - " + (x + i) + " принадлежит заданному диапазону");
            } else {
                System.out.println("Число - " + (x + i) + " находится за пределами заданного диапазона");
            }
        }
    }
}
