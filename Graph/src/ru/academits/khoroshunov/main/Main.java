package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.graph.Graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(new int[][]{{0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1, 0}
        });

        Consumer<Integer> printData = element -> System.out.print(element + ", ");

        System.out.println("Проверка метода \"Обход в ширину\".");
        graph.widthGoRound(printData);
        System.out.println();

        System.out.println("Проверка метода \"Обход в глубину\".");
        graph.deepGoRound(printData);
        System.out.println();

        System.out.println("Проверка метода \"Обход в глубину с рекурсией\".");
        graph.recursionDeepGoRound(printData);
    }
}
