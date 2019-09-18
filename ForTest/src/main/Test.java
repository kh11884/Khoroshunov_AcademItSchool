package main;

import java.io.*;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(1.0);
        arrayList.add(1.1);
        arrayList.add(1.2);
        arrayList.add(1.3);
        arrayList.add(1.4);

        System.out.println(arrayList.toString());

        try (DataOutputStream stream =
                     new DataOutputStream(new FileOutputStream(".\\ForTest\\src\\files\\test.bin"))) {
            for (double record : arrayList) {
                System.out.println(record);
                stream.writeDouble(record);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Double> arrayListOut = new ArrayList<>();
        try (DataInputStream stream =
                     new DataInputStream(new FileInputStream(".\\ForTest\\src\\files\\test.bin"))) {
            while (stream.available() > 0) {
                double x = stream.readDouble();
                arrayListOut.add(x);
                System.out.println(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(arrayListOut.toString());


    }
}
