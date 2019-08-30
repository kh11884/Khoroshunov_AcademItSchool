package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.tree.Tree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка конструктора Tree()");
        Tree<Integer> tree = new Tree<>();
        Consumer<Integer> printData = element -> System.out.print(element + ", ");
        System.out.println();

        System.out.println("Проверка обхода в глубину с рекурсией для пустого дерева.");
        tree.recursionDeepGoRound(printData);
        System.out.println();

        System.out.println("Проверка обхода в глубину без рекурсии для пустого дерева.");
        tree.treeDeepGoRound(printData);
        System.out.println();

        System.out.println("Проверка обхода в ширину для пустого дерева.");
        tree.treeWidthGoRound(printData);
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
        tree.insert(5);
        tree.insert(8);
        tree.insert(22);
        tree.insert(15);
        tree.insert(2);
        tree.insert(14);
        tree.insert(18);
        tree.insert(17);
        tree.insert(19);

        tree.treeWidthGoRound(printData);
        System.out.println();

        System.out.println("Проверка метода getSize.");
        System.out.println(tree.getSize());
        System.out.println();

        Integer element = 15;
        System.out.println("проверка метода delete. Удалим элемент: " + element);
        System.out.println(tree.delete(element));
        tree.treeWidthGoRound(printData);
        System.out.println(" Количество элементов дерева: " + tree.getSize());
        System.out.println();

        int element1 = 1;
        System.out.println("Проверка метода contains. Найти элемент: " + element1);
        System.out.println(tree.contains(element1));
        tree.recursionDeepGoRound(printData);
        System.out.println(" Количество элементов дерева: " + tree.getSize());
        System.out.println();

        System.out.println("Проверка обхода в глубину с рекурсией.");
        tree.recursionDeepGoRound(printData);
        System.out.println();

        System.out.println("Проверка обхода в глубину без рекурсии.");
        tree.treeDeepGoRound(printData);
        System.out.println();

        System.out.println("Проверка обхода в ширину.");
        tree.treeWidthGoRound(printData);
        System.out.println();

        System.out.println("Проверка блока кода с бесконечным циклом");
        Tree<Integer> tree2 = new Tree<>();
        tree2.insert(1);
        tree2.insert(5);
        tree2.insert(15);
        tree2.insert(10);
        tree2.insert(20);

        tree2.treeWidthGoRound(System.out::println);
        System.out.println();

        tree2.delete(15);
        tree2.treeWidthGoRound(System.out::println);
        System.out.println();

        System.out.println("Проверка удаления разных элементов:");
        for (Integer i = 0; i < 24; i++) {
            System.out.println("Удалим элемент: " + i);
            System.out.println(tree.delete(i));
            tree.treeWidthGoRound(printData);
            System.out.println(" Количество элементов дерева: " + tree.getSize());
            System.out.println();
            System.out.println();
        }
        System.out.println("проверка метода delete. Удалим элемент: null");
        System.out.println(tree.delete(null));
        tree.treeWidthGoRound(printData);
        System.out.println(" Количество элементов дерева: " + tree.getSize());
    }
}
