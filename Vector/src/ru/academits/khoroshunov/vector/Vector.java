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
        if (n < coordinates.length) {
            this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
        } else {
            this.coordinates = Arrays.copyOf(coordinates, n);
        }
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

    private Vector getBigVector(Vector vector) {
        if (getSize() >= vector.getSize()) {
            return this;
        } else {
            return vector;
        }
    }

    private Vector getSmallVector(Vector vector) {
        if (getSize() < vector.getSize()) {
            return this;
        } else {
            return vector;
        }
    }

    public Vector add(Vector vector) {
        Vector bigVector = this.getBigVector(vector);
        Vector smallVector = this.getSmallVector(vector);
        int bigVectorSize = bigVector.getSize();
        int smallVectorSize = smallVector.getSize();

        double[] result = new double[bigVectorSize];
        for (int i = 0; i < bigVectorSize; i++) {
            if (i < smallVectorSize) {
                result[i] = bigVector.coordinates[i] + smallVector.coordinates[i];
            } else {
                result[i] = bigVector.coordinates[i];
            }
        }
        this.coordinates = result;
        return this;
    }

    public Vector subtract(Vector vector) {
        Vector bigVector = this.getBigVector(vector);
        Vector smallVector = this.getSmallVector(vector);
        int bigVectorSize = bigVector.getSize();
        int smallVectorSize = smallVector.getSize();

        double[] result = new double[bigVectorSize];
        for (int i = 0; i < bigVectorSize; i++) {
            if (i < smallVectorSize) {
                result[i] = coordinates[i] - vector.coordinates[i];
            } else {
                if (vector.equals(smallVector)) {
                    result[i] = coordinates[i];
                } else {
                    result[i] = vector.coordinates[i] * -1;
                }

            }
        }
        this.coordinates = result;
        return this;
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            coordinates[i] = coordinates[i] * scalar;
        }
        return this;
    }

    public Vector reverse() {
        for (int i = 0; i < getSize(); i++) {
            coordinates[i] = coordinates[i] * -1;
        }
        return this;
    }

    public double getLength() {
        double value = 0;
        for (double d: coordinates) {
            value += d * d;
        }
        return Math.sqrt(value);
    }

    public double getCoordinate(int index) {
        if (index < 0 || index >= this.getSize()) {
            throw new ArrayIndexOutOfBoundsException("Значение индекса не принадлежит размерности вектора.");
        }
        return coordinates[index];
    }

    public void setCoordinate(int index, double coordinate) {
        if (index < 0 || index >= this.getSize()) {
            throw new ArrayIndexOutOfBoundsException("Значение индекса не принадлежит размерности вектора.");
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
        return vector1.add(vector2);
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        return vector1.subtract(vector2);
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
