package ru.academits.khoroshunov.matrix;

import ru.academits.khoroshunov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Размерность матрицы не может быть меньше 1.");
        }
        rows = new Vector[m];
        for (int i = 0; i < m; i++) {
            rows[i] = new Vector(n);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getHeight()];
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        for (int i = 0; i < height; i++) {
            rows[i] = new Vector(width);
            rows[i] = rows[i].add(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Размерность матрицы не может быть нулевой");
        }
        rows = new Vector[matrix.length];
        int maxLength = 0;
        for (double[] array : matrix) {
            if (maxLength < array.length) {
                maxLength = array.length;
            }
        }
        int height = getHeight();
        for (int i = 0; i < height; i++) {
            rows[i] = new Vector(maxLength, matrix[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null || vectors.length == 0) {
            throw new IllegalArgumentException("Размерность матрицы не может быть нулевой");
        }
        rows = new Vector[vectors.length];
        int maxLength = 0;
        for (Vector vector : vectors) {
            if (maxLength < vector.getSize()) {
                maxLength = vector.getSize();
            }
        }
        int height = getHeight();
        for (int i = 0; i < height; i++) {
            rows[i] = new Vector(maxLength);
            rows[i] = rows[i].add(vectors[i]);
        }
    }

    public int getHeight() {
        return rows.length;
    }

    public int getWidth() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getHeight()) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности высоты матрицы.");
        }
        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= getHeight()) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности высоты матрицы.");
        }
        if (vector.getSize() != getWidth()) {
            throw new IllegalArgumentException("Неверная размерность вектора. Передайте вектор размерности " + rows[0].getSize());
        }
        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getWidth()) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности высоты матрицы.");
        }
        double[] values = new double[getHeight()];
        int height = getHeight();
        for (int i = 0; i < height; i++) {
            values[i] = rows[i].getCoordinate(index);
        }
        return new Vector(values);
    }

    public Matrix transpose() {
        Matrix result = new Matrix(getHeight(), getWidth());
        int width = getWidth();
        for (int i = 0; i < width; i++) {
            result.setRow(i, getColumn(i));
        }
        rows = result.rows;
        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(scalar);
        }
        return this;
    }

    private static double roundToThreeDigits(double value) {
        return Math.round(value * 1000.0) / 1000.0;
    }

    public double getDeterminant() {
        if (rows == null || getHeight() == 0 || getHeight() != getWidth()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для квадратной матрицы");
        }
        if (getHeight() == 1) {
            return rows[0].getCoordinate(0);
        }
        if (getHeight() == 2) {
            return roundToThreeDigits(rows[0].getCoordinate(0) * rows[1].getCoordinate(1) -
                    rows[0].getCoordinate(1) * rows[1].getCoordinate(0));
        }
        Matrix subMatrix = new Matrix(getHeight() - 1, getHeight() - 1);
        double det = 0;
        int height = getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                if (j != i) {
                    for (int k = 1; k < height; k++) {
                        if (j < i) {
                            subMatrix.rows[j].setCoordinate(k - 1, rows[j].getCoordinate(k));
                        } else {
                            subMatrix.rows[j - 1].setCoordinate(k - 1, rows[j].getCoordinate(k));
                        }
                    }
                }
            }
            if (i % 2 == 0) {
                det += rows[i].getCoordinate(0) * subMatrix.getDeterminant();
            } else {
                det -= rows[i].getCoordinate(0) * subMatrix.getDeterminant();
            }
        }
        return roundToThreeDigits(det);
    }


    public String toString() {
        int iMax = getHeight() - 1;
        StringBuilder b = new StringBuilder();
        b.append('{');
        for (int i = 0; ; i++) {
            b.append(rows[i].toString());
            if (i == iMax) {
                return b.append('}').toString();
            }
            b.append(", ");
        }
    }

    public Matrix multiplyByVector(Vector vector) {
        if (getWidth() != 1) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для матрицы из одного столбца");
        }
        Matrix result = new Matrix(vector.getSize(), getHeight());
        int height = getHeight();
        int vectorLength = vector.getSize();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < vectorLength; j++) {
                result.rows[i].setCoordinate(j, rows[i].getCoordinate(0) * vector.getCoordinate(j));
            }
        }
        rows = result.rows;
        return this;
    }

    public Matrix getSum(Matrix matrix) {
        if (getHeight() != matrix.getHeight() || getWidth() != matrix.getWidth()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для одинаковых матриц.");
        }
        int height = getHeight();
        for (int i = 0; i < height; i++) {
            rows[i].add(matrix.rows[i]);
        }
        return this;
    }

    public Matrix subtract(Matrix matrix) {
        if (getHeight() != matrix.getHeight() || getWidth() != matrix.getWidth()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для одинаковых матриц.");
        }
        int height = getHeight();
        for (int i = 0; i < height; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
        return this;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        return new Matrix(matrix1).getSum(matrix2);
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        return new Matrix(matrix1).subtract(matrix2);
    }

    public static Matrix getMatrixMultiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getWidth() != matrix2.getHeight()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для согласованных матриц.");
        }
        int height = matrix1.getHeight();
        int width = matrix2.getWidth();
        Matrix result = new Matrix(width, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result.rows[i].setCoordinate(j, Vector.getScalarMultiply(matrix1.getRow(i), matrix2.getColumn(j)));
            }
        }
        return result;
    }
}
