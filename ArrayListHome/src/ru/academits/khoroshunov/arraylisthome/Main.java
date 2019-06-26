package ru.academits.khoroshunov.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "ArrayListHome\\src\\ru\\academits\\khoroshunov\\arraylisthome\\ArrayListHome.txt";
        ArrayList<Integer> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(inputFilePath))) {
            while (scanner.hasNext()) {
                numbers.add(Integer.valueOf(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Список, прочитанный из файла. Количество значений: " + numbers.size());
        System.out.println(numbers);
        System.out.println();

        for(int i = 0; i < numbers.size(); i++){
            if(numbers.get(i)%2 == 0){
                numbers.remove(i);
                i--;
            }
        }
        System.out.println("Список после удаления четных чисел. Количество значений: " + numbers.size());
        System.out.println(numbers);
        System.out.println();

        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        for (int value: numbers) {
            if(!uniqueNumbers.contains(value)){
                uniqueNumbers.add(value);
            }
        }
        System.out.println("Список из уникальных значений. Количество значений: " + uniqueNumbers.size());
        System.out.println(uniqueNumbers);
    }
}


