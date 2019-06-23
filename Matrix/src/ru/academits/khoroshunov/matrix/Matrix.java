package ru.academits.khoroshunov.matrix;

import ru.academits.khoroshunov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int columnsQuantity, int rowsQuantity) {
        if (columnsQuantity <= 0 || rowsQuantity <= 0) {
            throw new IllegalArgumentException("Размерность матрицы не может быть меньше 1.");
        }

        rows = new Vector[rowsQuantity];
        for (int i = 0; i < rowsQuantity; i++) {
            rows[i] = new Vector(columnsQuantity);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsQuantity()];
        int rowsQuantity = matrix.getRowsQuantity();
        int columnsQuantity = matrix.getColumnsQuantity();
        for (int i = 0; i < rowsQuantity; i++) {
            rows[i] = new Vector(columnsQuantity);
            rows[i] = rows[i].add(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrix) {
        int rowsQuantity = matrix.length;
        if (rowsQuantity == 0) {
            throw new IllegalArgumentException("Размерность матрицы не может быть нулевой");
        }

        int maxLength = 0;
        for (double[] array : matrix) {
            if (maxLength < array.length) {
                maxLength = array.length;
            }
        }

        rows = new Vector[rowsQuantity];
        for (int i = 0; i < rowsQuantity; i++) {
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

        int rowsQuantity = getRowsQuantity();
        for (int i = 0; i < rowsQuantity; i++) {
            rows[i] = new Vector(maxLength);
            rows[i] = rows[i].add(vectors[i]);
        }
    }

    public int getRowsQuantity() {
        return rows.length;
    }

    public int getColumnsQuantity() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowsQuantity()) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности высоты матрицы.");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= getRowsQuantity()) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности высоты матрицы.");
        }
        if (vector.getSize() != getColumnsQuantity()) {
            throw new IllegalArgumentException("Неверная размерность вектора. Передайте вектор размерности " + rows[0].getSize());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsQuantity()) {
            throw new IndexOutOfBoundsException("Значение индекса не принадлежит размерности высоты матрицы.");
        }

        double[] values = new double[getRowsQuantity()];
        int rowsQuantity = getRowsQuantity();
        for (int i = 0; i < rowsQuantity; i++) {
            values[i] = rows[i].getCoordinate(index);
        }

        return new Vector(values);
    }

    private void transposeSquareMatrix(int matrixSize) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                double intermediateValue = rows[i].getCoordinate(j);
                rows[i].setCoordinate(j, rows[j].getCoordinate(i));
                rows[j].setCoordinate(i, intermediateValue);
            }
        }
    }

    public Matrix transpose() {
        int columnsQuantity = getColumnsQuantity();
        int rowsQuantity = getRowsQuantity();
        if (rowsQuantity == columnsQuantity) {
            transposeSquareMatrix(rowsQuantity);
        } else {
            Vector[] result = new Vector[columnsQuantity];
            for (int i = 0; i < columnsQuantity; i++) {
                result[i] = getColumn(i);
            }
            rows = result;
        }

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
        if (getRowsQuantity() != getColumnsQuantity()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для квадратной матрицы");
        }
        if (getRowsQuantity() == 1) {
            return rows[0].getCoordinate(0);
        }
        if (getRowsQuantity() == 2) {
            return roundToThreeDigits(rows[0].getCoordinate(0) * rows[1].getCoordinate(1) -
                    rows[0].getCoordinate(1) * rows[1].getCoordinate(0));
        }

        Matrix subMatrix = new Matrix(getRowsQuantity() - 1, getRowsQuantity() - 1);
        double det = 0;
        int rowsQuantity = getRowsQuantity();

        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < rowsQuantity; j++) {
                if (j != i) {
                    for (int k = 1; k < rowsQuantity; k++) {
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
        int iMax = getRowsQuantity() - 1;
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

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsQuantity() != vector.getSize()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна количеству столбцов матрицы.");
        }

        int rowsQuantity = getRowsQuantity();
        Vector result = new Vector(rowsQuantity);
        int vectorLength = vector.getSize();
        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < vectorLength; j++) {
                result.setCoordinate(i, result.getCoordinate(i) + rows[i].getCoordinate(j)*vector.getCoordinate(j));
            }
        }

        return result;
    }


    public Matrix getSum(Matrix matrix) {
        if (getRowsQuantity() != matrix.getRowsQuantity() || getColumnsQuantity() != matrix.getColumnsQuantity()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для одинаковых матриц.");
        }

        int rowsQuantity = getRowsQuantity();
        for (int i = 0; i < rowsQuantity; i++) {
            rows[i].add(matrix.rows[i]);
        }
        return this;
    }

    public Matrix subtract(Matrix matrix) {
        if (getRowsQuantity() != matrix.getRowsQuantity() || getColumnsQuantity() != matrix.getColumnsQuantity()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для одинаковых матриц.");
        }

        int rowsQuantity = getRowsQuantity();
        for (int i = 0; i < rowsQuantity; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
        return this;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsQuantity() != matrix2.getRowsQuantity() || matrix1.getColumnsQuantity() != matrix2.getColumnsQuantity()) {
            throw new IllegalArgumentException("Неверная размерность матриц. Используйте для одинаковых матриц.");
        }

        return new Matrix(matrix1).getSum(matrix2);
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsQuantity() != matrix2.getRowsQuantity() || matrix1.getColumnsQuantity() != matrix2.getColumnsQuantity()) {
            throw new IllegalArgumentException("Неверная размерность матриц. Используйте для одинаковых матриц.");
        }

        return new Matrix(matrix1).subtract(matrix2);
    }

    public static Matrix getMatrixMultiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException("Неверная размерность матрицы. Используйте для согласованных матриц.");
        }

        int rowsQuantity = matrix1.getRowsQuantity();
        int columnsQuantity = matrix2.getColumnsQuantity();
        Matrix result = new Matrix(columnsQuantity, rowsQuantity);

        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < columnsQuantity; j++) {
                result.rows[i].setCoordinate(j, Vector.getScalarMultiply(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }
        return result;
    }
}
