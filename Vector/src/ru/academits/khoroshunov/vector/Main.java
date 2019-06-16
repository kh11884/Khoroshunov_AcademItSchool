package ru.academits.khoroshunov.vector;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[] myArray =  {1, 2, 3 ,4, 5};
        double[] myArray1 = {6, 7, 8 ,9, 10};


        System.out.println(Arrays.toString(myArray));

        Vector vector1 = new Vector(myArray);
        System.out.println(vector1);

        Vector vector2 = new Vector(myArray1);

        Vector vector3 = vector1.addVector(vector2);
        System.out.println(vector3);

    }
}
