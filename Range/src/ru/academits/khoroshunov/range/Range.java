package ru.academits.khoroshunov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        if (from < to) {
            this.from = from;
            this.to = to;
        } else {
            this.from = to;
            this.to = from;
        }
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double x) {
        return x >= from && x <= to;
    }

    public Range getIntersection(Range r) {
        if (to <= r.from || from >= r.to) {
            return null;
        } else {
            return new Range(Math.max(from, r.from), Math.min(to, r.to));
        }
    }

    public Range[] getUnion(Range r) {
        if (to < r.from || from > r.to) {
            return new Range[]{
                    new Range(Math.min(from, r.from), Math.min(to, r.to)),
                    new Range(Math.max(from, r.from), Math.max(to, r.to))
            };
        } else {
            return new Range[]{new Range(Math.min(from, r.from), Math.max(to, r.to))};
        }
    }

    public Range[] getDifference(Range r) {
        if (to <= r.from || from >= r.to) {
            return new Range[]{new Range(from, to)};
        }
        if (from >= r.from && to <= r.to) {
            return new Range[]{};
        }
        if (from < r.from && to <= r.to) {
            return new Range[]{new Range(from, r.from)};
        }
        if (to > r.to && from >= r.from) {
            return new Range[]{new Range(r.to, to)};
        } else {
            return new Range[]{
                    new Range(from, r.from),
                    new Range(r.to, to)
            };
        }
    }
}
