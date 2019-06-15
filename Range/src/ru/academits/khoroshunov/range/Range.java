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

    public Range getIntersectionRange(Range r) {
        if (from <= r.from && to >= r.to) {
            return new Range(r.from, r.to);
        }
        if (from >= r.from && to <= r.to) {
            return new Range(from, to);
        }
        if (from <= r.from && to <= r.to && to >= r.from) {
            return new Range(r.from, to);
        }
        if (from >= r.from && to >= r.to && from <= r.to) {
            return new Range(from, r.to);
        } else {
            return null;
        }
    }

    public Range[] getUnionRanges(Range r) {
        if (from >= r.from && to <= r.to) {
            return new Range[]{new Range(r.from, r.to)};
        }
        if (from <= r.from && to >= r.to) {
            return new Range[]{new Range(from, to)};
        }
        if (from <= r.from && to <= r.to && to >= r.from) {
            return new Range[]{new Range(from, r.to)};
        }
        if (from >= r.from && to >= r.to && from <= r.to) {
            return new Range[]{new Range(r.from, to)};
        } else {
            if (to <= r.from) {
                return new Range[]{
                        new Range(from, to),
                        new Range(r.from, r.to)
                };
            } else {
                return new Range[]{
                        new Range(r.from, r.to),
                        new Range(from, to)
                };
            }
        }
    }

    public Range[] getOddRanges(Range r) {
        if (from >= r.from && to <= r.to) {
            return null;
        }
        if (to < r.from || from > r.to) {
            return new Range[]{new Range(from, to)};
        }
        if (from < r.from && to <= r.to && to >= r.from) {
            return new Range[]{new Range(from, r.from)};
        }
        if (from >= r.from && to > r.to && from <= r.to) {
            return new Range[]{new Range(r.to, to)};
        } else {
            return new Range[]{
                    new Range(from, r.from),
                    new Range(r.to, to)
            };
        }
    }
}
