package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.matrix.Matrix;
import ru.academits.khoroshunov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        // проверка конструктора матрицы nxm
        Matrix m1 = new Matrix(2, 3);
        System.out.println("Matrix(int n, int m): " + m1);

        // проверка конструктора матрицы (Matrix)
        Matrix m2 = new Matrix(m1);
        System.out.println("Matrix(Matrix): " + m2);

        // проверка конструктора Matrix(double[][])
        double[][] twoLevelArray = {{1, 2}, {3}};
        Matrix m3 = new Matrix(twoLevelArray);
        System.out.println("Matrix(double[][]): " + m3);

        // проверка конструктора Matrix(Vector[])
        double[] myArray = {1, 2, 3, 4, 5};
        Vector vector1 = new Vector(myArray);
        double[] myArray1 = {6, 7, 3};
        Vector vector2 = new Vector(3, myArray1);
        Vector[] vectorArray = {vector1, vector2};
        Matrix m4 = new Matrix(vectorArray);
        System.out.println("Matrix(Vector[]): " + m4);

        // проверка метода получить размер матрицы
        System.out.println("Размер матрицы. размер n: " + m4.getSize().getN() + ", размер m: "+ m4.getSize().getM());
    }
}
