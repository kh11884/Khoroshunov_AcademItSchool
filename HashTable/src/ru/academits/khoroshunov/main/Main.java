package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.hashtable.HashTable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка конструктора без аргументов");
        HashTable hashTable1 = new HashTable();
        System.out.println(hashTable1);
        System.out.println();

        int size = 5;
        System.out.println("Проверка конструктора с размером ХэшТаблицы: " + size);
        HashTable hashTable = new HashTable(5);
        System.out.println(hashTable);
        System.out.println();

        System.out.println("Проверка метода add. Добавляем несколько элементов");
        hashTable.add("a");
        hashTable.add("b");
        hashTable.add("c");
        hashTable.add("d");
        hashTable.add("e");
        hashTable.add("f");
        hashTable.add("g");
        hashTable.add("h");
        hashTable.add("i");
        hashTable.add("j");
        hashTable.add("m");
        hashTable.add("m");
        hashTable.add("m");
        System.out.println(hashTable);
        System.out.println();

        System.out.println("Проверка метода remove");
        String valueForTestRemove = "m";
        System.out.println("Исходная HashTable " + hashTable);
        System.out.println("Удаляем элемент \"" + valueForTestRemove + "\"");
        hashTable.remove(valueForTestRemove);
        System.out.println("Возвращаемый результат " + hashTable);
        System.out.println();

        System.out.println("Проверка метода isEmpty");
        System.out.println(hashTable.isEmpty());
        System.out.println();

        String forTestContains = "s";
        System.out.println("Проверка метода contains. Содержит ли ХэшТаблица элемент \"" + forTestContains + "\"");
        System.out.println(hashTable.contains(forTestContains));
        System.out.println();

        System.out.println("Проверка итератора. Выводим содержимое ХэшТаблицы:");
        Iterator i = hashTable.iterator();
        for (; i.hasNext(); ) {
            String text = String.valueOf(i.next());
            System.out.print(text + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("Проверка метода toArray()");
        System.out.println(Arrays.toString(hashTable.toArray()));
        System.out.println();

        List collection1 = Arrays.asList("x", "y", "s");
        System.out.println("Проверка метода addAll. Добавляем в ХэшТаблицу элементы: " + collection1.toString());
        System.out.println("до операции");
        System.out.println(hashTable);
        hashTable.addAll(collection1);
        System.out.println("после операции");
        System.out.println(hashTable);
        System.out.println();

        String forTestRemove = "m";
        System.out.println("Проверка метода remove. Удаляем элемент: \"" + forTestRemove + "\"");
        System.out.println("до операции");
        System.out.println(hashTable);
        hashTable.remove(forTestRemove);
        System.out.println("после операции");
        System.out.println(hashTable);
        System.out.println();

        List collection2 = Arrays.asList("x", "y", "b");
        System.out.println("Проверка метода retainAll. Удаляем элементы не принадлежащие коллекции " + collection2.toString());
        System.out.println("до операции");
        System.out.println(hashTable);
        System.out.println("возвращенный результат: " + hashTable.retainAll(collection2));
        System.out.println("после операции");
        System.out.println(hashTable);
        System.out.println();

        List collection3 = Arrays.asList(1, 2, 3);
        System.out.println("Проверка метода retainAll с несовместимым типом. Удаляем элементы не принадлежащие коллекции " + collection2.toString());
        System.out.println("до операции");
        System.out.println(hashTable);
        System.out.println("возвращенный результат: " + hashTable.retainAll(collection3));
        System.out.println("после операции");
        System.out.println(hashTable);
        System.out.println();

        hashTable.addAll(Arrays.asList("x", "y", "a", "b", "d", "f", "k", "l"));
        List collection4 = Arrays.asList("x", "y", "b");
        System.out.println("Проверка метода removeAll. Удаляем элементы принадлежащие коллекции " + collection4.toString());
        System.out.println("до операции");
        System.out.println(hashTable);
        System.out.println("возвращенный результат: " + hashTable.removeAll(collection4));
        System.out.println("после операции");
        System.out.println(hashTable);
        System.out.println();

        List collection5 = Arrays.asList(1, 2, 3);
        System.out.println("Проверка метода removeAll с несовместимым типом. Удаляем элементы принадлежащие коллекции " + collection5.toString());
        System.out.println("до операции");
        System.out.println(hashTable);
        System.out.println("возвращенный результат: " + hashTable.removeAll(collection5));
        System.out.println("после операции");
        System.out.println(hashTable);
        System.out.println();

        System.out.println("Проверка метода clear");
        System.out.println("до операции");
        System.out.println(hashTable);
        hashTable.clear();
        System.out.println("после операции");
        System.out.println(hashTable);
        System.out.println();

        hashTable.addAll(Arrays.asList("x", "y", "a", "b", "d", "f", "k", "r"));
        List forTestContainAll = Arrays.asList("x", "y", "0", "b", "d", "f", "k", "x");
        System.out.println("Проверка метода containAll. Проверяем есть ли в ХэшТаблице все элементы из коллекции: " + forTestContainAll.toString());
        System.out.println("ХэшТаблица: " + hashTable);
        System.out.println("возвращенный результат: " + hashTable.containsAll(forTestContainAll));
        System.out.println();

        System.out.println("Проверка метода <T> T[] toArray(T[] a)");
        String[] array1 = new String[]{"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
        System.out.println("ХэшТаблица: " + hashTable);
        System.out.println("Исходный массив: " + Arrays.toString(array1));
        //noinspection SuspiciousToArrayCall
        System.out.println("Получившийся массив: " + Arrays.toString(hashTable.toArray(array1)));
    }
}
