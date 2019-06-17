package ru.academits.khoroshunov.matrix;

import ru.academits.khoroshunov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] lines;

    public Matrix(int n, int m) {
        lines = new Vector[m];
        for (int i = 0; i < m; i++) {
            lines[i] = new Vector(n);
        }
    }

    public Matrix(Matrix matrix) {
        lines = Arrays.copyOf(matrix.lines, matrix.lines.length);
    }

    public Matrix(double[][] matrix) {
        lines = new Vector[matrix.length];
        int maxLength = 0;
        for (double[] array : matrix) {
            if (maxLength < array.length) {
                maxLength = array.length;
            }
        }
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Vector(maxLength, matrix[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        lines = new Vector[vectors.length];
        int maxLength = 0;
        for (Vector vector : vectors) {
            if (maxLength < vector.getSize()) {
                maxLength = vector.getSize();
            }
        }
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Vector(maxLength);
            lines[i] = lines[i].add(vectors[i]);
        }
    }

    public MatrixSize getSize() {
        return new MatrixSize(lines[0].getSize(), lines.length);
    }

    public String toString() {
        if (lines == null) {
            return "null";
        }
        int iMax = lines.length - 1;
        if (iMax == -1) {
            return "{}";
        }
        StringBuilder b = new StringBuilder();
        b.append('{');
        for (int i = 0; ; i++) {
            b.append(lines[i].toString());
            if (i == iMax) {
                return b.append('}').toString();
            }
            b.append(", ");
        }
    }
}
