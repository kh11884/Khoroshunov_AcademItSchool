package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка конструкторов.");
        Vector vector1 = new Vector(2);
        System.out.println("Vector(n). Вектор1: " + vector1 + " размерность вектора: " + vector1.getSize());

        Vector vector2 = new Vector(vector1);
        System.out.println("Vector(Vector). Вектор2: " + vector2 + " размерность вектора: " + vector2.getSize());

        double[] myArray = {1, 2, 3, 4, 5};
        Vector vector3 = new Vector(myArray);
        System.out.println("Vector(double[]). Вектор3: " + vector3 + " размерность вектора: " + vector3.getSize());

        double[] myArray1 = {6, 7, 3};
        Vector vector4 = new Vector(3, myArray1);
        System.out.println("Vector(n, double[]). Вектор4: " + vector4 + " размерность вектора: " + vector4.getSize());
        System.out.println();

        System.out.println("Проверка нестатических методов:");
        Vector sumVectors = vector3.add(vector4);
        System.out.println("Сложения векторов. Результирующий вектор: " + sumVectors);

        Vector subtractVectors = new Vector(new double[]{1, 2}).subtract(new Vector(new double[]{5, 3, 3}));
        System.out.println("Вычитания векторов. Результирующий вектор: " + subtractVectors);

        Vector scalarMultiplyVector = vector4.multiplyByScalar(3);
        System.out.println("Умножения вектора на скаляр 3. Результирующий вектор: " + scalarMultiplyVector);

        Vector reversibleVector = vector4.reverse();
        System.out.println("Разворот вектора. Результирующий вектор: " + reversibleVector);

        double vectorLength = vector4.getLength();
        System.out.println("Рассчет длины вектора 4: " + vectorLength);

        vector4.setCoordinate(2, 2.5);
        System.out.println("Присвоение и получение координаты 2,5 для вектора4 по индексу2: " + vector4.getCoordinate(2));
        System.out.println();

        System.out.println("Проверка нестатических методов:");
        Vector sumVectors1 = Vector.getSum(vector3, vector4);
        System.out.println("Сложения векторов 3 и 4. Результирующий вектор: " + sumVectors1);

        Vector oddVectors1 = Vector.subtract(vector3, vector4);
        System.out.println("Вычитания векторов 3 и 4. Результирующий вектор: " + oddVectors1);

        double scalarMultiply = Vector.getScalarMultiply(vector3, vector4);
        System.out.println("Скалярного поизведения векторов 3 и 4. Результирующий вектор: " + scalarMultiply);
    }
}
