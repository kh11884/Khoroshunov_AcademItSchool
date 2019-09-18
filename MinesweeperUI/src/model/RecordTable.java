package model;

import gui.MineField;

import java.io.*;
import java.util.ArrayList;

public class RecordTable {
    private ArrayList<Double> recordTable;
    private String type;

    public RecordTable(String type) {
        this.type = type;
        recordTable = new ArrayList<>(10);
        try (DataInputStream stream = new DataInputStream(new FileInputStream(".\\MinesweeperUI\\src\\resources\\easy.txt"))) {
            while (stream.available() > 0) {
                double record = stream.readDouble();
                recordTable.add(record);
                System.out.println(record);
            }

            System.out.println(recordTable.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Double> getRecordTable() {
        return recordTable;
    }

    public String getType() {
        return type;
    }

    public void addNewRecord(double result) {
        System.out.println(result);
        for (int i = 0; i < 10; i++) {
            System.out.println();
            if (recordTable.size() == 0) {
                recordTable.add(result);
                break;
            }
            if (recordTable.size() == i) {
                recordTable.add(i, result);
                break;
            }
            if (recordTable.get(i) > result) {
                recordTable.add(i, result);
                break;
            }
        }
        if (recordTable.size() == 11) {
            recordTable.remove(10);
        }

        try (DataOutputStream stream =
                     new DataOutputStream(new FileOutputStream(".\\MinesweeperUI\\src\\resources\\easy.txt"))) {
            for (double record : MineField.recordTable.getRecordTable()) {
                System.out.println(record);
                stream.writeDouble(record);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
