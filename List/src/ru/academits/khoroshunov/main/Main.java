package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.item.RandomLinkListItem;
import ru.academits.khoroshunov.list.RandomLinkList;
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

        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println();

        int index = 6;
        System.out.println("Метод get: получаем элемент по индексу " + index);
        System.out.println(list.get(index));
        System.out.println();

        System.out.println("Метод getFirst: получаем первый элемент списка.");
        System.out.println(list.getFirst());
        System.out.println();

        String testValue = "X";
        index = 6;
        System.out.println("Метод set: устанавливаем значение " + testValue + " по индексу " + index);
        String deletedItem = list.set(index, testValue);
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println("Затертый элемент: " + deletedItem);
        System.out.println();

        index = 0;
        System.out.println("Метод delete: удаляем элемент по индексу " + index);
        deletedItem = list.delete(index);
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println("Удаленный элемент: " + deletedItem);
        System.out.println();

        testValue = "X";
        index = 0;
        System.out.println("Метод insert: вставляем элемент " + testValue + " по индексу " + index);
        list.insert(index, testValue);
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println();

        testValue = "X";
        System.out.println("Метод deleteAll: удаляем все элементы со значением " + testValue);
        boolean da = list.deleteAll(testValue);
        System.out.println(list + " Количество элементов списка: " + list.getSize());
        System.out.println("Был ли удален хотя бы один элемент: " + da);
        System.out.println();

        testValue = "f";
        System.out.println("Метод delete: удаляем первое вхождение элемента " + testValue);
        da = list.delete("f");
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
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");

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
        System.out.println("Исходный список: " + list);
        System.out.println("Конечный список: " + list1);
        list1 = list.copy();
        System.out.println("После применения метода:");
        System.out.println("Исходный список: " + list);
        System.out.println("Конечный список: " + list1);
        System.out.println("Проверяем, что получилась копия списка. Изменяем один элемент исходного списка");
        list.set(1, "xxx");
        System.out.println("Исходный список: " + list);
        System.out.println("Конечный список: " + list1);
        System.out.println("-----------------------------------------");
        System.out.println();

        System.out.println("Проверка копирования с доп ссылкой.");
        RandomLinkList<String> randomLinkList = new RandomLinkList<>();
        RandomLinkListItem<String> a = new RandomLinkListItem<>("Первый");
        RandomLinkListItem<String> b = new RandomLinkListItem<>("Второй");
        RandomLinkListItem<String> c = new RandomLinkListItem<>("Третий");
        RandomLinkListItem<String> d = new RandomLinkListItem<>("Четвертый");
        RandomLinkListItem<String> e = new RandomLinkListItem<>("Пятый");
        RandomLinkListItem<String> f = new RandomLinkListItem<>("Шестой");
        RandomLinkListItem<String> g = new RandomLinkListItem<>("Седьмой");
        a.setRandomLink(c);
        b.setRandomLink(f);
        c.setRandomLink(g);
        d.setRandomLink(a);
        e.setRandomLink(a);
        f.setRandomLink(e);
        randomLinkList.add(a);
        randomLinkList.add(b);
        randomLinkList.add(c);
        randomLinkList.add(d);
        randomLinkList.add(e);
        randomLinkList.add(f);
        randomLinkList.add(g);

        System.out.println("Исходный список:");
        System.out.println(randomLinkList);
        RandomLinkList<String> copyRandomLinkList;
        copyRandomLinkList = randomLinkList.copy();
        System.out.println("Копия списка:");
        System.out.println(copyRandomLinkList);
        System.out.println();

        System.out.println("Данные элементов ссылок исходного списка:");
        randomLinkList.printDataLinkedElement();
        System.out.println("Данные элементов ссылок копии списка:");
        copyRandomLinkList.printDataLinkedElement();
        System.out.println();

        System.out.println("Меняем одну из ссылок исходного списка и один элемент исходного списка");
        b.setRandomLink(null);
        c.setData("XXX");

        System.out.println("Данные элементов ссылок исходного списка:");
        randomLinkList.printDataLinkedElement();
        System.out.println("Данные элементов ссылок копии списка:");
        copyRandomLinkList.printDataLinkedElement();
    }
}
