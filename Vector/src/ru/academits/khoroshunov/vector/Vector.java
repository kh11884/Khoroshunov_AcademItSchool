package ru.academits.khoroshunov.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть меньше 1.");
        }
        coordinates = new double[n];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть меньше 1.");
        }
        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector(int n, double[] coordinates) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть меньше 1.");
        }
        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        int iMax = getSize() - 1;
        StringBuilder b = new StringBuilder();
        b.append('{');
        for (int i = 0; ; i++) {
            b.append(coordinates[i]);
            if (i == iMax) {
                return b.append('}').toString();
            }
            b.append(", ");
        }
    }

    public Vector add(Vector vector) {
        for (int i = 0; i < Math.min(getSize(), vector.getSize()); i++) {
            coordinates[i] += vector.coordinates[i];
        }
        if (getSize() < vector.getSize()) {
            double[] intermediateValue = Arrays.copyOf(coordinates, vector.getSize());
            System.arraycopy(vector.coordinates, getSize(), intermediateValue, getSize(), vector.getSize() - getSize());
            coordinates = intermediateValue;
        }
        return this;
    }

    public Vector subtract(Vector vector) {
        for (int i = 0; i < Math.min(getSize(), vector.getSize()); i++) {
            coordinates[i] -= vector.coordinates[i];
        }
        if (getSize() < vector.getSize()) {
            double[] intermediateValue = Arrays.copyOf(coordinates, vector.getSize());
            for(int i = getSize(); i < vector.getSize(); i++){
                intermediateValue[i] -= vector.coordinates[i];
            }
            coordinates = intermediateValue;
        }
        return this;
    }


    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            coordinates[i] *= scalar;
        }
        return this;
    }

    public Vector reverse() {
        multiplyByScalar(-1);
        return this;
    }

    public double getLength() {
        double value = 0;
        for (double d : coordinates) {
            value += d * d;
        }
        return Math.sqrt(value);
    }

    public double getCoordinate(int index) {
        if (index < 0 || index >= this.getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности вектора.");
        }
        return coordinates[index];
    }

    public void setCoordinate(int index, double coordinate) {
        if (index < 0 || index >= this.getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности вектора.");
        }
        coordinates[index] = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;
        return Arrays.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        return new Vector(vector1).add(vector2);
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        return new Vector(vector1).subtract(vector2);
    }

    public static double getScalarMultiply(Vector vector1, Vector vector2) {
        int smallVectorSize = Math.min(vector1.getSize(), vector2.getSize());
        double result = 0;
        for (int i = 0; i < smallVectorSize; i++) {
            result += vector1.coordinates[i] * vector2.coordinates[i];
        }
        return result;
    }
}
