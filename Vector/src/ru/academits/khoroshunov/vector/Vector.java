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
        if (n <= 0 || coordinates.length == 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть меньше 1.");
        }
        if (n < coordinates.length) {
            throw new IllegalArgumentException("Размерность вектора не может быть меньше длины переданного массива.");
        }
        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        if (coordinates == null) {
            return "null";
        }
        int iMax = getSize() - 1;
        if (iMax == -1) {
            return "{}";
        }
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
        Vector bigVector;
        Vector smallVector;
        if (getSize() > vector.getSize()) {
            bigVector = new Vector(coordinates);
            smallVector = new Vector(vector.coordinates);
        } else {
            bigVector = new Vector(vector.coordinates);
            smallVector = new Vector(coordinates);
        }
        Vector result = new Vector(bigVector.getSize());
        for (int i = 0; i < result.getSize(); i++) {
            if (i < smallVector.getSize()) {
                result.coordinates[i] = bigVector.coordinates[i] + smallVector.coordinates[i];
            } else {
                result.coordinates[i] = bigVector.coordinates[i];
            }
        }
        return result;
    }

    public Vector subtract(Vector vector) {
        Vector bigVector;
        Vector smallVector;
        if (getSize() > vector.getSize()) {
            bigVector = new Vector(coordinates);
            smallVector = new Vector(vector.coordinates);
        } else {
            bigVector = new Vector(vector.coordinates);
            smallVector = new Vector(coordinates);
        }
        Vector result = new Vector(bigVector.getSize());
        for (int i = 0; i < result.getSize(); i++) {
            if (i < smallVector.getSize()) {
                result.coordinates[i] = bigVector.coordinates[i] - smallVector.coordinates[i];
            } else {
                result.coordinates[i] = bigVector.coordinates[i];
            }
        }
        return result;
    }

    public Vector multiplyByScalar(double scalar) {
        Vector result = new Vector(getSize());
        for (int i = 0; i < result.getSize(); i++) {
            result.coordinates[i] = coordinates[i] * scalar;
        }
        return result;
    }

    public Vector reverse() {
        Vector result = new Vector(this.getSize());
        for (int i = 0; i < result.getSize(); i++) {
            result.coordinates[i] = coordinates[i] * -1;
        }
        return result;
    }

    public double getLength() {
        double value = 0;
        for (int i = 0; i < this.getSize(); i++) {
            value += coordinates[i] * coordinates[i];
        }
        return Math.sqrt(value);
    }

    public double getIndexCoordinate(int index) {
        if (index < 0 || index > this.getSize() - 1) {
            throw new IllegalArgumentException("Значение индекса не принадлежит размерности вектора.");
        }
        return coordinates[index];
    }

    public void setIndexCoordinate(int index, double coordinate) {
        if (index < 0 || index > this.getSize() - 1) {
            throw new IllegalArgumentException("Значение индекса не принадлежит размерности вектора.");
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
        Vector bigVector;
        Vector smallVector;
        if (vector1.getSize() > vector2.getSize()) {
            bigVector = new Vector(vector1.coordinates);
            smallVector = new Vector(vector2.coordinates);
        } else {
            bigVector = new Vector(vector2.coordinates);
            smallVector = new Vector(vector1.coordinates);
        }
        Vector result = new Vector(bigVector.getSize());
        for (int i = 0; i < result.getSize(); i++) {
            if (i < smallVector.getSize()) {
                result.coordinates[i] = bigVector.coordinates[i] + smallVector.coordinates[i];
            } else {
                result.coordinates[i] = bigVector.coordinates[i];
            }
        }
        return result;
    }

    public static Vector getOdd(Vector vector1, Vector vector2) {
        Vector bigVector;
        Vector smallVector;
        if (vector1.getSize() > vector2.getSize()) {
            bigVector = new Vector(vector1.coordinates);
            smallVector = new Vector(vector2.coordinates);
        } else {
            bigVector = new Vector(vector2.coordinates);
            smallVector = new Vector(vector1.coordinates);
        }
        Vector result = new Vector(bigVector.getSize());
        for (int i = 0; i < result.getSize(); i++) {
            if (i < smallVector.getSize()) {
                result.coordinates[i] = bigVector.coordinates[i] - smallVector.coordinates[i];
            } else {
                result.coordinates[i] = bigVector.coordinates[i];
            }
        }
        return result;
    }

    public static double getScalarMultiply(Vector vector1, Vector vector2) {
        Vector bigVector;
        Vector smallVector;
        if (vector1.getSize() > vector2.getSize()) {
            bigVector = new Vector(vector1.coordinates);
            smallVector = new Vector(vector2.coordinates);
        } else {
            bigVector = new Vector(vector2.coordinates);
            smallVector = new Vector(vector1.coordinates);
        }
        double result = 0;
        for (int i = 0; i < smallVector.getSize(); i++) {
            result += smallVector.coordinates[i] * bigVector.coordinates[i];
        }
        return result;
    }
}
