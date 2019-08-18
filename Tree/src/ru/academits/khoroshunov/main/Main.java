package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.tree.Tree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка конструктора Tree()");
        Tree<Integer> tree = new Tree<>();
        Consumer<Integer> printData = element -> System.out.print(element + ", ");
        System.out.println();

        System.out.println("Проверка конструктора Tree(Comparator)");
        Tree<Integer> treeWithComparator = new Tree<>(Integer::compareTo);
        treeWithComparator.insert(123);
        treeWithComparator.treeDeepGoRound(printData);
        System.out.println();
        System.out.println();

        System.out.println("Проверка метода insert. Добавим несколько элементов");
        tree.insert(10);
        tree.insert(5);
        tree.insert(1);
        tree.insert(13);
        tree.insert(15);
        tree.insert(12);
        tree.insert(2);
        tree.insert(8);
        tree.insert(null);
        tree.printTreeRecursionDeepGoRound(printData);
        System.out.println();

        System.out.println("Проверка метода getSize.");
        System.out.println(tree.getSize());
        System.out.println();

        Integer element = 1;
        System.out.println("проверка метода delete. Удалим элемент: " + element);
        System.out.println(tree.delete(element));
        tree.printTreeRecursionDeepGoRound(printData);
        System.out.println(" Количество элементов дерева: " + tree.getSize());
        System.out.println();

        int element1 = 5;
        System.out.println("Проверка метода isContains. Найти элемент: " + element1);
        System.out.println(tree.isContains(element1));
        tree.printTreeRecursionDeepGoRound(printData);
        System.out.println(" Количество элементов дерева: " + tree.getSize());
        System.out.println();

        System.out.println("Проверка обхода в глубину с рекурсией.");
        tree.printTreeRecursionDeepGoRound(printData);
        System.out.println();

        System.out.println("Проверка обхода в глубину без рекурсии.");
        tree.treeDeepGoRound(printData);
        System.out.println();

        System.out.println("Проверка обхода в ширину.");
        tree.treeWidthGoRound(printData);
        System.out.println();
    }
}
