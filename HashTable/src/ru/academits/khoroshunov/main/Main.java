package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.hashtable.HashTable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка конструктора без аргументов");
        HashTable<String> hashTable = new HashTable<>();
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        int size = 5;
        System.out.println("Проверка конструктора с размером ХэшТаблицы: " + size);
        hashTable = new HashTable<>(5);
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        System.out.println("Проверка метода add. Добавляем несколько элементов");
        hashTable.add(null);
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
        hashTable.add("y");
        hashTable.add("m");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        System.out.println("Проверка метода toArray()");
        System.out.println(Arrays.toString(hashTable.toArray()));
        System.out.println();

        System.out.println("Проверка метода remove");
        String valueForTestRemove = "m";
        System.out.println("Исходная HashTable " + hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println("Удаляем элемент \"" + valueForTestRemove + "\"");
        hashTable.remove(valueForTestRemove);
        System.out.println("Возвращаемый результат " + hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
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

        List<String> collection1 = Arrays.asList("x", "y", "s");
        System.out.println("Проверка метода addAll. Добавляем в ХэшТаблицу элементы: " + collection1.toString());
        System.out.println("до операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        hashTable.addAll(collection1);
        System.out.println("после операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        String forTestRemove = "m";
        System.out.println("Проверка метода remove. Удаляем элемент: \"" + forTestRemove + "\"");
        System.out.println("до операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        hashTable.remove(forTestRemove);
        System.out.println("после операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        List<String> collection2 = Arrays.asList("x", "y", "b");
        System.out.println("Проверка метода retainAll. Удаляем элементы не принадлежащие коллекции " + collection2.toString());
        System.out.println("до операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println("возвращенный результат: " + hashTable.retainAll(collection2));
        System.out.println("после операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        List<Integer> collection3 = Arrays.asList(null, 2, 4);
        System.out.println("Проверка метода retainAll с несовместимым типом. Удаляем элементы не принадлежащие коллекции " + collection3.toString());
        System.out.println("до операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        //noinspection SuspiciousMethodCalls
        System.out.println("возвращенный результат: " + hashTable.retainAll(collection3));
        System.out.println("после операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        hashTable.addAll(Arrays.asList("x", "y", "a", "b", "d", "f", "k", "l"));
        List<String> collection4 = Arrays.asList("x", "y", "b");
        System.out.println("Проверка метода removeAll. Удаляем элементы принадлежащие коллекции " + collection4.toString());
        System.out.println("до операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println("возвращенный результат: " + hashTable.removeAll(collection4));
        System.out.println("после операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        List<Integer> collection5 = Arrays.asList(null, 2, 3);
        System.out.println("Проверка метода removeAll с несовместимым типом. Удаляем элементы принадлежащие коллекции " + collection5.toString());
        System.out.println("до операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        //noinspection SuspiciousMethodCalls
        System.out.println("возвращенный результат: " + hashTable.removeAll(collection5));
        System.out.println("после операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        System.out.println("Проверка метода clear");
        System.out.println("до операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        hashTable.clear();
        System.out.println("после операции");
        System.out.println(hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println();

        hashTable.addAll(Arrays.asList("x", "y", "a", "b", "d", "f", "k", "r"));
        List forTestContainAll = Arrays.asList("x", "y", "0", "b", "d", "f", "k", "x");
        System.out.println("Проверка метода containAll. Проверяем есть ли в ХэшТаблице все элементы из коллекции: " + forTestContainAll.toString());
        System.out.println("ХэшТаблица: " + hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println("возвращенный результат: " + hashTable.containsAll(forTestContainAll));
        System.out.println();

        System.out.println("Проверка метода <T> T[] toArray(T[] a)");
        String[] array1 = new String[]{"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
        System.out.println("ХэшТаблица: " + hashTable + " Количество элементов хэш-таблицы: " + hashTable.size());
        System.out.println("Исходный массив: " + Arrays.toString(array1));
        System.out.println("Получившийся массив: " + Arrays.toString(hashTable.toArray(array1)));
    }
}
