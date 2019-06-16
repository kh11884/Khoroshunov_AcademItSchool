package ru.academits.khoroshunov.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Длина вектора не может быть меньше 1.");
        }
        this.vector = new double[n];
    }

    public Vector(Vector v) {
        this.vector = Arrays.copyOf(v.vector, v.vector.length);
    }

    public Vector(double[] array) {
        this.vector = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Длина вектора не может быть меньше 1.");
        }
        this.vector = Arrays.copyOf(array, n);
    }

    public int getSize() {
        return this.vector.length;
    }

    @Override
    public String toString() {
        if (this.vector == null) {
            return "null";
        }
        int iMax = this.vector.length - 1;
        if (iMax == -1) {
            return "{}";
        }
        StringBuilder b = new StringBuilder();
        b.append('{');
        for (int i = 0; ; i++) {
            b.append(this.vector[i]);
            if (i == iMax) {
                return b.append('}').toString();
            }
            b.append(", ");
        }
    }

    public Vector addVector(Vector v){
        Vector result = new Vector(this.vector.length + v.vector.length);
        System.arraycopy(this.vector, 0, result.vector, 0, this.vector.length);
        System.arraycopy(v.vector, 0, result.vector, this.vector.length, v.vector.length);
        return  result;
    }
}
