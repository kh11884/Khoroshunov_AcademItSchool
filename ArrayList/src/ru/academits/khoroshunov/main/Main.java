package ru.academits.khoroshunov.main;

import ru.academits.khoroshunov.arraylist.ArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка создания списка на массиве. Конструктор: ArrayList()");
        ArrayList<String> arrayList1 = new ArrayList<>();
        System.out.println(arrayList1);
        System.out.println();

        System.out.println("Проверка создания списка на массиве. Конструктор: ArrayList(int capacity)");
        ArrayList arrayList2 = new ArrayList(12);
        System.out.println(arrayList2);
        System.out.println();

        arrayList1.add("a");
        arrayList1.add("b");
        arrayList1.add("b");
        arrayList1.add("c");
        arrayList1.add("d");
        arrayList1.add("e");
        arrayList1.add("f");
        System.out.println("Исходный список на массиве");
        System.out.println(arrayList1 + " Длина списка на массиве: " + arrayList1.size());
        System.out.println();

        System.out.println("Проверка метора contains(Object o)");
        String value1 = "b";
        String value2 = "h";
        System.out.println("Результат работы метода, проверяем есть ли \"" + value1 + "\" - " + arrayList1.contains(value1));
        System.out.println("Результат работы метода, проверяем есть ли \"" + value2 + "\" - " + arrayList1.contains(value2));
        System.out.println();

        System.out.println("Проверка итератора. Выводим содержимое списка:");
        Iterator i = arrayList1.iterator();
        for (; i.hasNext(); ) {
            String text = String.valueOf(i.next());
            System.out.print(text + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("Проверка метода E[] toArray()");
        System.out.println(Arrays.toString(arrayList1.toArray()));
        System.out.println();

        int index1 = 0;
        System.out.println("Проверка метора remove. Удалим элемент индекса: " + index1);
        arrayList1.remove(index1);
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println();

        List collection1 = Arrays.asList("b", "d");
        System.out.println("Проверка метора addAll. Добавим коллекцию: " + collection1.toString());
        //noinspection unchecked
        arrayList1.addAll(collection1);
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println();

        List collection2 = Arrays.asList("x", "y");
        int index2 = 0;
        System.out.println("Проверка метора addAll(int index, Collection c). Добавим коллекцию: " + collection2.toString() + " по индексу " + index2);
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        //noinspection unchecked
        arrayList1.addAll(index2, collection2);
        System.out.println("после операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println();

        List collection3 = Arrays.asList("x", "y", "b");
        System.out.println("Проверка метода retainAll. Удаляем элементы не принадлежащие коллекции " + collection3.toString());
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        arrayList1.retainAll(collection3);
        System.out.println("после операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println();

        arrayList1.add(0, "a");
        arrayList1.add(3, "c");
        arrayList1.add(5, "e");
        arrayList1.add(6, "f");
        List collection4 = Arrays.asList("x", "b");
        System.out.println("Проверка метода removeAll. Удаляем элементы принадлежащие коллекции " + collection4.toString());
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        arrayList1.removeAll(collection4);
        System.out.println("после операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println();

        arrayList1.add(1, "b");
        arrayList1.add(2, "b");
        arrayList1.add(4, "d");
        List collection5 = Arrays.asList("f", "b");
        System.out.println("Проверка метода containAll. Проверяем есть ли все элементы принадлежащие коллекции " + collection5.toString());
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println(arrayList1.containsAll(collection5));
        System.out.println("после операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println();

        System.out.println("Проверка метода <T> T[] toArray(T[] a)");
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        String[] array1 = new String[]{"1", "1", "1", "1", "1"};
        System.out.println("Получившийся массив ");
        System.out.println(Arrays.toString(arrayList1.toArray(array1)));
        System.out.println();

        int index3 = 7;
        System.out.println("Проверка метода get");
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Элемент по индексу " + index3 + " - " + arrayList1.get(index3));
        System.out.println();

        int index4 = 0;
        String element1 = "e";
        System.out.println("Проверка метода set");
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Вставляем элемент \"" + element1 + "\" по индексу - " + index4);
        String result = arrayList1.set(index4, element1);
        System.out.println("после операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Метод set вовзратил значение: " + result);
        System.out.println();

        int index5 = 7;
        System.out.println("Проверка метода remove");
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Удаляем элемент по индексу - " + index5);
        String element2 = arrayList1.remove(index5);
        System.out.println("после операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Удаленный элемент: " + element2);
        System.out.println();

        String element3 = "e";
        System.out.println("Проверка метода indexOf");
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Элемент \"" + element3 + "\" находится по индексу: " + arrayList1.indexOf(element3));
        System.out.println("после операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println();

        String element4 = "x";
        System.out.println("Проверка метода LastIndexOf");
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Элемент \"" + element4 + "\" последний индекс вхождения: " + arrayList1.lastIndexOf(element4));
        System.out.println("после операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println();

        System.out.println("Проверка метода ensureCapacity");
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Длина зарезервированного массива: " + arrayList1.getArrayLengthForTest());
        arrayList1.ensureCapacity(15);
        System.out.println("после операции.");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Длина зарезервированного массива: " + arrayList1.getArrayLengthForTest());
        System.out.println();

        System.out.println("Проверка метода trimToSize");
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Длина зарезервированного массива: " + arrayList1.getArrayLengthForTest());
        arrayList1.trimToSize();
        System.out.println("после операции.");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Длина зарезервированного массива: " + arrayList1.getArrayLengthForTest());
        System.out.println();

        System.out.println("Проверка метода clear");
        System.out.println("до операции");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Содержимое массива: " + Arrays.toString(arrayList1.getArrayForTest()));
        arrayList1.clear();
        System.out.println("после операции.");
        System.out.println(arrayList1 + " Длина списка: " + arrayList1.size());
        System.out.println("Содержимое массива: " + Arrays.toString(arrayList1.getArrayForTest()));
    }
}
