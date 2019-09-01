package ru.academits.khoroshunov.lambda;

import ru.academits.khoroshunov.person.Person;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(new Person("Андрей", 40),
                new Person("Иван", 38),
                new Person("Петр", 15),
                new Person("Николай", 29),
                new Person("Андрей", 13),
                new Person("Игорь", 35),
                new Person("Андрей", 13),
                new Person("Ольга", 14),
                new Person("Сергей", 11),
                new Person("Денис", 18));

        //  А) получить список уникальных имен
        //noinspection Convert2MethodRef
        Stream<String> personsNames = personList.stream().map(person -> person.getName()).distinct();

        // Б) вывести список уникальных имен
        System.out.println(personsNames.collect(Collectors.joining(", ", "Имена: ", ".")));
        System.out.println();

        // В) получить список людей младше 18, посчитать для них средний возраст
        System.out.print("Средний возраст людей моложе 18 лет: ");
        //noinspection Convert2MethodRef,OptionalGetWithoutIsPresent
        System.out.println(personList.stream().filter(person -> person.getAge() < 18).mapToDouble(person -> person.getAge()).average().getAsDouble());
        System.out.println();

        // Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        //noinspection Convert2MethodRef
//        Map<String, Double> personsByName = personList.stream().collect(Collectors.groupingBy(person -> person.getName(),personList.stream()
//                ));
//        Map<Integer, List<Person>> personsByAge = persons .stream().collect(Collectors.(p -> p.getAge()));
//        System.out.println(personsByName);


        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите целое число - сколько элементов корней нужно вычислить: ");
        int sqrtQuantity = scanner.nextInt() + 1;
        DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt).limit(sqrtQuantity).forEach(System.out::println);

        System.out.print("Введите целое число - сколько элементов чисел фибоначи нужно вычислить: ");
        int fiboQuantity = scanner.nextInt() + 1;
        Stream.iterate(new int[]{1, 1}, p -> new int[]{p[1], p[0] + p[1]}).limit(fiboQuantity).forEach(p -> System.out.println(p[0]));
    }

}
