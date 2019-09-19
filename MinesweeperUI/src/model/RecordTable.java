package model;

import gui.GameField;

import java.io.*;
import java.util.ArrayList;

public class RecordTable {
    private ArrayList<Integer> recordTable;
    private String type;

    public RecordTable(String type) {
        this.type = type;
        recordTable = new ArrayList<>(10);
        try (DataInputStream stream = new DataInputStream(new FileInputStream(".\\MinesweeperUI\\src\\resources\\" + type + ".bin"))) {
            while (stream.available() > 0) {
                int record = stream.readInt();
                recordTable.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getRecordTable() {
        return recordTable;
    }

    public void addNewRecord(int result) {
        for (int i = 0; i < 10; i++) {
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
                     new DataOutputStream(new FileOutputStream(".\\MinesweeperUI\\src\\resources\\" + type + ".bin"))) {
            for (int record : GameField.recordTable.getRecordTable()) {
                stream.writeInt(record);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
