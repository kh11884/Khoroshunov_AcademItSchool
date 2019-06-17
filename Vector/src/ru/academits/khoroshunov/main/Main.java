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
        vector1 = new Vector(myArray);
        System.out.println("Vector(double[]). Вектор3: " + vector1 + " размерность вектора: " + vector1.getSize());

        double[] myArray1 = {6, 7, 3};
        vector2 = new Vector(3, myArray1);
        System.out.println("Vector(n, double[]). Вектор4: " + vector2 + " размерность вектора: " + vector2.getSize());
        System.out.println();

        System.out.println("ПРОВЕРКА НЕСТАТИЧЕСКИХ МЕТОДОВ:");
        System.out.println("Сложение векторов. До операции:");
        vector1 = new Vector(new double[]{2, 2, 2, 2});
        vector2 = new Vector(new double[]{1, 1});
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        Vector result = vector1.add(vector2);
        System.out.println("После операции:");
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        System.out.println("Возвращаемый результат: " + result);
        System.out.println();

        System.out.println("Вычитание векторов. До операции:");
        vector1 = new Vector(new double[]{2, 2, 2, 2});
        vector2 = new Vector(new double[]{1, 1});
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        result = vector1.subtract(vector2);
        System.out.println("После операции:");
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        System.out.println("Возвращаемый результат: " + result);
        System.out.println();

        System.out.println("Умножение вектора на скаляр. До операции:");
        vector1 = new Vector(new double[]{2, 2, 2, 2});
        double scalar = 3;
        System.out.println("Вектор: " + vector1);
        System.out.println("Скаляр: " + scalar);
        result = vector1.multiplyByScalar(scalar);
        System.out.println("После операции:");
        System.out.println("Вектор: " + vector1);
        System.out.println("Возвращаемый результат: " + result);
        System.out.println();

        System.out.println("Разворот вектора. До операции:");
        vector1 = new Vector(new double[]{2, 2, 2, 2});
        System.out.println("Вектор: " + vector1);
        result = vector1.reverse();
        System.out.println("После операции:");
        System.out.println("Вектор: " + vector1);
        System.out.println("Возвращаемый результат: " + result);
        System.out.println();

        System.out.println("Рассчет длины вектора. До операции:");
        vector1 = new Vector(new double[]{2, 2, 2});
        System.out.println("Вектор: " + vector1);
        double vectorLength = vector1.getLength();
        System.out.println("После операции:");
        System.out.println("Вектор: " + vector1);
        System.out.println("Возвращаемый результат: " + vectorLength);
        System.out.println();

        System.out.println("Установить координату по индексу. До операции:");
        vector1 = new Vector(new double[]{1, 2, 3});
        double value = 2.5;
        int index = 2;
        System.out.println("Вектор: " + vector1);
        System.out.println("Значение: " + value);
        System.out.println("Индекс: " + index);
        vector1.setCoordinate(index, value);
        System.out.println("После операции:");
        System.out.println("Вектор: " + vector1);
        System.out.println();

        System.out.println("Получить координату по индексу. До операции:");
        vector1 = new Vector(new double[]{1, 2, 3});
        index = 2;
        System.out.println("Вектор: " + vector1);
        System.out.println("Индекс: " + index);
        value = vector1.getCoordinate(index);
        System.out.println("После операции:");
        System.out.println("Вектор: " + vector1);
        System.out.println("Возвращаемый результат: " + value);
        System.out.println();

        System.out.println("ПРОВЕРКА СТАТИЧЕСКИХ МЕТОДОВ:");
        System.out.println("Сложение векторов. До операции:");
        vector1 = new Vector(new double[]{2, 2, 2, 2});
        vector2 = new Vector(new double[]{1, 1});
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        result = Vector.getSum(vector1, vector2);
        System.out.println("После операции:");
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        System.out.println("Возвращаемый результат: " + result);
        System.out.println();

        System.out.println("Вычитание векторов. До операции:");
        vector1 = new Vector(new double[]{2, 2, 2, 2});
        vector2 = new Vector(new double[]{1, 1});
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        result = Vector.subtract(vector1, vector2);
        System.out.println("После операции:");
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        System.out.println("Возвращаемый результат: " + result);
        System.out.println();

        System.out.println("Скалярное произведение векторов. До операции:");
        vector1 = new Vector(new double[]{2, 2, 2, 2});
        vector2 = new Vector(new double[]{1, 1});
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        scalar = Vector.getScalarMultiply(vector1, vector2);
        System.out.println("После операции:");
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        System.out.println("Возвращаемый результат: " + scalar);
    }
}

