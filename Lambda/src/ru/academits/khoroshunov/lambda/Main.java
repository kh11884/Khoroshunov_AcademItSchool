package ru.academits.khoroshunov.lambda;

import ru.academits.khoroshunov.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(new Person("Андрей", 45),
                new Person("Иван", 38),
                new Person("Петр", 15),
                new Person("Николай", 29),
                new Person("Андрей", 14),
                new Person("Игорь", 35),
                new Person("Ольга", 10),
                new Person("Ольга", 13),
                new Person("Сергей", 11),
                new Person("Денис", 18));

        System.out.println("Получаем и выводим уникальные имена.");
        //noinspection Convert2MethodRef
        Stream<String> personsNames = personList.stream()
                .map(person -> person.getName())
                .distinct();
        System.out.println(personsNames.collect(Collectors.joining(", ", "Имена: ", ".")));
        System.out.println();

        System.out.println("Получаем список людей моложе 18 лет и выводим для них средний возраст: ");
        List<Person> under18YearsOldPerson = personList.stream()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors.toList());
        //noinspection Convert2MethodRef
        System.out.println(under18YearsOldPerson.stream()
                .map(person -> person.getName())
                .collect(Collectors.joining(", ")));
        //noinspection Convert2MethodRef, OptionalGetWithoutIsPresent
        System.out.println("Средний возраст: "
                + under18YearsOldPerson.stream()
                .mapToDouble(person -> person.getAge())
                .average()
                .getAsDouble()
                + " лет.");
        System.out.println();

        System.out.println("При помощи группировки получаем Map, в котором ключи – имена, а значения – средний возраст.");
        Map<String, Double> personsByName = personList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println(personsByName);

        System.out.println("Имена людей от 20 до 45 по убыванию возраста.");
        //noinspection Convert2MethodRef
        personList.stream()
                .filter(age -> age.getAge() >= 20 && age.getAge() < 45)
                .sorted((age1, age2) -> age2.getAge() - age1.getAge())
                .map(name -> name.getName())
                .forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите целое число - сколько элементов корней нужно вычислить: ");
        int sqrtQuantity = scanner.nextInt() + 1;
        DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(sqrtQuantity)
                .forEach(System.out::println);

        System.out.print("Введите целое число - сколько элементов чисел фибоначчи нужно вычислить: ");
        int fibonacciQuantity = scanner.nextInt();
        Stream.iterate(new int[]{0, 1}, p -> new int[]{p[1], p[0] + p[1]})
                .map(p -> p[0])
                .limit(fibonacciQuantity)
                .forEach(System.out::println);
    }
}
