package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.matrix.Matrix;
import ru.academits.khoroshunov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("ПРОВЕРКА КОНСТРУКТОРОВ.");
        Matrix m1 = new Matrix(2, 3);
        System.out.println("Matrix(int n, int m): " + m1);

        Matrix m2 = new Matrix(m1);
        System.out.println("Matrix(Matrix): " + m2);

        double[][] twoLevelArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 1}};
        Matrix m3 = new Matrix(twoLevelArray);
        System.out.println("Matrix(double[][]): " + m3);

        Vector vector1 = new Vector(new double[]{1, 2, 3, 4, 5});
        Vector vector2 = new Vector(new double[]{6, 7, 3});
        Vector[] vectorArray = {vector1, vector2};
        Matrix m4 = new Matrix(vectorArray);
        System.out.println("Matrix(Vector[]): " + m4);
        System.out.println();

        System.out.println("ПРОВЕРКА НЕСТАТИЧЕСКИХ МЕТОДОВ.");
        System.out.println("Получить размер матрицы. До операции:");
        m1 = new Matrix(new double[][]{{1, 2}, {4, 5}, {4, 5}});
        System.out.println("матрица: " + m1);
        System.out.println("Возвращаемый результат: размер n: " + m1.getColumnsQuantity() + ", размер m: " + m1.getRowsQuantity());
        System.out.println("После операции:");
        System.out.println("матрица: " + m1);
        System.out.println();

        System.out.println("Получить вектор-строку. До операции:");
        m1 = new Matrix(new double[][]{{1, 2}, {4, 5}, {6, 7}});
        System.out.println("матрица: " + m1);
        int index = 1;
        System.out.println("Возвращаемый результат: Вектор-строка " + index + ": " + m1.getRow(index));
        System.out.println("После операции:");
        System.out.println("матрица: " + m1);
        System.out.println();

        System.out.println("Задать вектор-строку. До операции:");
        m1 = new Matrix(new double[][]{{1, 2}, {4, 5}, {4, 5}});
        System.out.println("матрица: " + m1);
        index = 1;
        Vector v1 = new Vector(new double[]{1, 1});
        System.out.println("Добавляем вектор " + v1 + " в строку " + index);
        m1.setRow(index, v1);
        System.out.println("После операции:");
        System.out.println("матрица: " + m1);
        System.out.println();

        System.out.println("Получить векстор-столбец. До операции:");
        m1 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println("матрица: " + m1);
        index = 1;
        System.out.println("Возвращаемый результат: Вектор-столбец " + index + ": " + m1.getColumn(index));
        System.out.println("После операции:");
        System.out.println("матрица: " + m1);
        System.out.println();

        System.out.println("Транспонировать матрицу. До операции:");
        m1 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println("матрица: " + m1);
        System.out.println("Возвращаемый результат: " + m1.transpose());
        System.out.println("После операции:");
        System.out.println("матрица: " + m1);
        System.out.println();

        System.out.println("Умножение на скаляр. До операции:");
        m1 = new Matrix(new double[][]{{1, 2}, {4, 5}, {4, 5}});
        int scalar = 3;
        System.out.println("матрица: " + m1 + " скаляр: " + scalar);
        System.out.println("Возвращаемый результат: " + m1.multiplyByScalar(scalar));
        System.out.println("После операции:");
        System.out.println("матрица: " + m1);
        System.out.println();

        System.out.println("Вычисление детерминанта. До операции:");
        m1 = new Matrix(new double[][]{{1, 2, 8}, {4, 5, 8}, {4, 5, 5}});
        System.out.println("матрица: " + m1);
        System.out.println("Возвращаемый результат: " + m1.getDeterminant());
        System.out.println("После операции:");
        System.out.println("матрица: " + m1);
        System.out.println();

        System.out.println("Умножение матрицы на вектор-столбец. До операции:");
        m1 = new Matrix(new double[][]{{1, 2, 8}, {4, 5, 6}, {4, 5, 5}});
        v1 = new Vector(new double[]{-1, 2, 0});
        System.out.println("матрица: " + m1 + " Вектор: " + v1);
        System.out.println("Возвращаемый результат: " + m1.multiplyByVector(v1));
        System.out.println("После операции:");
        System.out.println("матрица: " + m1 + " Вектор: " + v1);
        System.out.println();

        System.out.println("Сложение матриц. До операции:");
        m1 = new Matrix(new double[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        m2 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 1}});
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println("Возвращаемый результат: " + m1.getSum(m2));
        System.out.println("После операции:");
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println();

        System.out.println("Вычитание матриц. До операции:");
        m1 = new Matrix(new double[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        m2 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 1}});
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println("Возвращаемый результат: " + m1.subtract(m2));
        System.out.println("После операции:");
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println("---------------------");
        System.out.println();

        System.out.println("ПРОВЕРКА СТАТИЧЕСКИХ МЕТОДОВ.");
        System.out.println("Сложение матриц. До операции:");
        m1 = new Matrix(new double[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        m2 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 1}});
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println("Возвращаемый результат: " + Matrix.getSum(m1, m2));
        System.out.println("После операции:");
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println();

        System.out.println("Вычитание матриц. До операции:");
        m1 = new Matrix(new double[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        m2 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 1}});
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println("Возвращаемый результат: " + Matrix.subtract(m1, m2));
        System.out.println("После операции:");
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println();

        System.out.println("Умножение матриц. До операции:");
        m1 = new Matrix(new double[][]{{1, 1, 1, 4}, {1, 1, 1, 5}, {1, 1, 1, 6}});
        m2 = new Matrix(new double[][]{{1, 2}, {4, 5}, {7, 8}, {4, 2}});
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println("Возвращаемый результат: " + Matrix.getMatrixMultiply(m1, m2));
        System.out.println("После операции:");
        System.out.println("матрица1: " + m1 + " Матрица2: " + m2);
        System.out.println();
    }
}
