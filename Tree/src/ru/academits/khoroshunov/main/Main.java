package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.tree.Tree;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка конструктора Tree(data)");
        Tree<Integer> tree = new Tree<>(10);
        tree.printTreeRecursionDeepGoRound();
        System.out.println();

        System.out.println("Проверка метода insert. Добавим несколько элементов");
        tree.insert(5);
        tree.insert(1);
        tree.insert(13);
        tree.insert(15);
        tree.insert(12);
        tree.insert(2);
        tree.insert(8);
        tree.printTreeRecursionDeepGoRound();
        System.out.println();

        System.out.println("Проверка метода getSize.");
        System.out.println(tree.getSize());
        System.out.println();

        int element = 8;
        System.out.println("проверка метода delete. Удалим элемент: " + element);
        tree.delete(element);
        tree.printTreeRecursionDeepGoRound();
        System.out.println(" Количество элементов дерева: " + tree.getSize());
        System.out.println();

        int element1 = 5;
        System.out.println("Проверка метода isContains. Найти элемент: " + element1);
        System.out.println(tree.isContains(element1));
        tree.printTreeRecursionDeepGoRound();
        System.out.println(" Количество элементов дерева: " + tree.getSize());
        System.out.println();

        System.out.println("Проверка обхода в глубину с рекурсией.");
        tree.printTreeRecursionDeepGoRound();
        System.out.println();

        System.out.println("Проверка обхода в глубину без рекурсии.");
        tree.printTreeDeepGoRound();
        System.out.println();

        System.out.println("Проверка обхода в ширину.");
        tree.printTreeWidthGoRound();
        System.out.println();
    }
}
