package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.addFirst("first");

        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println();

        int index = 0;
        System.out.println("Метод get: получаем элемент по индексу " + index);
        System.out.println(list.get(index));
        System.out.println();

        System.out.println("Метод getFirst: получаем первый элемент списка.");
        System.out.println(list.getFirst());
        System.out.println();

        String testValue = "X";
        index = 7;
        System.out.println("Метод set: устанавливаем значение " + testValue + " по индексу " + index);
        String deletedItem = list.set(index, testValue);
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println("Затертый элемент: " + deletedItem);
        System.out.println();

        index = 6;
        System.out.println("Метод delete: удаляем элемент по индексу " + index);
        deletedItem = list.delete(index);
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println("Удаленный элемент: " + deletedItem);
        System.out.println();

        testValue = "Y";
        index = 7;
        System.out.println("Метод insert: вставляем элемент " + testValue + " по индексу " + index);
        list.insert(index, testValue);
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println();

        list.set(0, "f");
        list.set(3, "f");
        list.set(4, "f");
        System.out.println(list);
        testValue = "f";
        System.out.println("Метод deleteAll: удаляем все элементы со значением " + testValue);
        boolean da = list.deleteAll(testValue);
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println("Был ли удален хотя бы один элемент: " + da);
        System.out.println();

        testValue = "f";
        list.set(1, "f");
        System.out.println(list);
        System.out.println("Метод delete: удаляем первое вхождение элемента " + testValue);
        da = list.delete(testValue);
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println("Был ли удален элемент: " + da);
        System.out.println();

        System.out.println("Метод deleteFirst: удаляем первый элемент списка.");
        deletedItem = list.deleteFirst();
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println("Удаленный элемент: " + deletedItem);
        System.out.println();

        list.deleteFirst();
        list.deleteFirst();
        list.deleteFirst();
        list.add("a");
        list.add("b");
        list.add("с");

        System.out.println("Метод reverse.");
        System.out.println("Список до применения метода:");
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        list.reverse();
        System.out.println("Список после применения метода:");
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println();

        System.out.println("Метод copy.");
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>();
        System.out.println("До применения метода:");
        System.out.println("Исходный список: " + list + " Количество элементов: " + list.getSize());
        System.out.println("Конечный список: " + list1 + " Количество элементов: " + list1.getSize());
        list1 = list.copy();
        System.out.println("После применения метода:");
        System.out.println("Исходный список: " + list + " Количество элементов: " + list.getSize());
        System.out.println("Конечный список: " + list1 + " Количество элементов: " + list1.getSize());
        System.out.println("Проверяем, что получилась копия списка. Изменяем один элемент исходного списка");
        list.set(1, "xxx");
        System.out.println("Исходный список: " + list + " Количество элементов: " + list.getSize());
        System.out.println("Конечный список: " + list1 + " Количество элементов: " + list1.getSize());
    }
}
