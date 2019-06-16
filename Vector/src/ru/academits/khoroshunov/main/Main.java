package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(2);
        System.out.println(vector.getSize());
        Vector vectorCopy = new Vector(vector);
        System.out.println(vectorCopy);

        double[] myArray = {1, 2, 3, 4, 5};
        double[] myArray1 = {6, 7, 3};
        Vector vector1 = new Vector(5, myArray);
        Vector vector2 = new Vector(myArray1);

        // проверка перегрузки toString
        System.out.println("Вектор1 " + vector1);
        System.out.println("Вектор2 " + vector2);

        // проверка сложения векторов
        Vector sumVectors = vector1.add(vector2);
        System.out.println(sumVectors);

        // проверка вычитания векторов
        Vector subtractVectors = vector1.subtract(vector2);
        System.out.println(subtractVectors);

        // проверка умножения на скаляр
        Vector scalarMultiplyVector = vector2.multiplyByScalar(3);
        System.out.println(scalarMultiplyVector);

        // проверка реверсирования
        Vector reversibleVector = vector2.reverse();
        System.out.println(reversibleVector);

        //проверка расссчета длины вектора
        double vectorLength = vector2.getLength();
        System.out.println(vectorLength);

        //проверка установки и получения координаты по индексу
        subtractVectors.setIndexCoordinate(3, 2.5);
        System.out.println(subtractVectors.getIndexCoordinate(3));

    }
}
