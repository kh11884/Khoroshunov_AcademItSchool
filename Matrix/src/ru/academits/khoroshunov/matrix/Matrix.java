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
        if (matrix == null) {
            lines = null;
        } else {
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
        if(this.lines == null){
            throw new IllegalArgumentException("Матрица задана пустым массивом.");
        }
        return new MatrixSize(lines[0].getSize(), lines.length);
    }

    public Vector getLineVector(int index) {
        if (index < 0 || index >= lines.length) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности высоты матрицы.");
        }
        return lines[index];
    }

    public void setLineVector(int index, Vector vector) {
        if (index < 0 || index >= lines.length) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности высоты матрицы.");
        }
        if (vector.getSize() != lines[0].getSize()) {
            throw new IllegalArgumentException("Неверная размерность вектора. Передайте вектор размерности " + lines[0].getSize());
        }
        lines[index] = vector;
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index >= lines[0].getSize()) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности высоты матрицы.");
        }
        double[] values = new double[lines.length];
        for (int i = 0; i < lines.length; i++) {
            values[i] = lines[i].getCoordinate(index);
        }
        return new Vector(values);
    }

    public Matrix transpose() {
        Matrix result = new Matrix(lines.length, lines[0].getSize());
        for (int i = 0; i < lines[0].getSize(); i++) {
            result.setLineVector(i, getColumnVector(i));
        }
        this.lines = result.lines;
        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        for (Vector vector : lines) {
            vector.multiplyByScalar(scalar);
        }
        return this;
    }

    private static double roundToThreeDigits(double value) {
        return Math.round(value * 1000.0) / 1000.0;
    }

    public double getDeterminant() {
        if (lines == null || lines.length == 0 || lines.length != lines[0].getSize()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для квадратной матрицы");
        }
        if (lines.length == 1) {
            return lines[0].getCoordinate(0);
        }
        if (lines.length == 2) {
            return roundToThreeDigits(lines[0].getCoordinate(0) * lines[1].getCoordinate(1) -
                    lines[0].getCoordinate(1) * lines[1].getCoordinate(0));
        }
        Matrix subMatrix = new Matrix(lines.length - 1, lines.length - 1);
        double det = 0;
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines.length; j++) {
                if (j != i) {
                    for (int k = 1; k < lines.length; k++) {
                        if (j < i) {
                            subMatrix.lines[j].setCoordinate(k - 1, lines[j].getCoordinate(k));
                        } else {
                            subMatrix.lines[j - 1].setCoordinate(k - 1, lines[j].getCoordinate(k));
                        }
                    }
                }
            }
            if (i % 2 == 0) {
                det += lines[i].getCoordinate(0) * subMatrix.getDeterminant();
            } else {
                det -= lines[i].getCoordinate(0) * subMatrix.getDeterminant();
            }
        }
        return roundToThreeDigits(det);
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

    public Matrix multiplyByVector(Vector vector) {
        if (lines == null || lines.length == 0 || lines[0].getSize() != 1) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для матрицы из одного столбца");
        }
        Matrix result = new Matrix(vector.getSize(), lines.length);
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < vector.getSize(); j++) {
                result.lines[i].setCoordinate(j, lines[i].getCoordinate(0) * vector.getCoordinate(j));
            }
        }
        this.lines = result.lines;
        return this;
    }

    public Matrix getSum(Matrix matrix) {
        if (lines == null || lines.length == 0 || lines.length != matrix.lines.length
                || lines[0].getSize() != matrix.lines[0].getSize()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для одинаковых матриц.");
        }
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[0].getSize(); j++) {
                lines[i].setCoordinate(j, lines[i].getCoordinate(j) + matrix.lines[i].getCoordinate(j));
            }
        }
        return this;
    }

    public Matrix subtract(Matrix matrix) {
        if (lines == null || lines.length == 0 || lines.length != matrix.lines.length
                || lines[0].getSize() != matrix.lines[0].getSize()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для одинаковых матриц.");
        }
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[0].getSize(); j++) {
                lines[i].setCoordinate(j, lines[i].getCoordinate(j) - matrix.lines[i].getCoordinate(j));
            }
        }
        return this;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2){
        return new Matrix(matrix1).getSum(matrix2);
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2){
        return new Matrix(matrix1).subtract(matrix2);
    }

    public static Matrix getMatrixMultiply(Matrix matrix1, Matrix matrix2){
        if (matrix1.lines[0].getSize() != matrix2.lines.length) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для согласованных матриц.");
        }
        Matrix result = new Matrix( matrix2.lines[0].getSize(), matrix1.lines.length);
        for (int i = 0; i < matrix1.lines.length; i++) {
            for (int j = 0; j < matrix2.lines[0].getSize(); j++) {
                result.lines[i].setCoordinate(j, Vector.getScalarMultiply(matrix1.getLineVector(i),matrix2.getColumnVector(j)));
            }
        }
        return result;
    }

}
