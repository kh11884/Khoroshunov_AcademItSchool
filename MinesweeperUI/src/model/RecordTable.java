package model;

import gui.GameField;

import java.io.*;
import java.util.ArrayList;

public class RecordTable {
    private ArrayList<RecordNote> recordTable;
    private String type;
    private Integer worstRecord;

    public RecordTable(String type) {
        this.type = type;
        recordTable = new ArrayList<>(10);

        RecordNote recordNote = null;

        try (FileInputStream fileInputStream = new FileInputStream(".\\MinesweeperUI\\src\\resources\\" + type + ".bin");
             ObjectInputStream stream = new ObjectInputStream(fileInputStream)) {
            recordNote = (RecordNote) stream.readObject();
            recordTable.add(recordNote);
            while (fileInputStream.available() > 0) {
                recordNote = (RecordNote) stream.readObject();
                recordTable.add(recordNote);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка чтения файла таблицы рекордов. " + type + ".bin - " + e.getMessage());
        }
        if (!recordTable.isEmpty()) {
            worstRecord = recordNote.getRecordValue();
        }
    }

    public ArrayList<RecordNote> getRecordTable() {
        return recordTable;
    }

    public void addNewRecord(String name, int result) {
        for (int i = 0; i < 10; i++) {
            if (recordTable.size() == i) {
                recordTable.add(i, new RecordNote(name, result));
                break;
            }
            if (recordTable.get(i).getRecordValue() > result) {
                recordTable.add(i, new RecordNote(name, result));
                break;
            }
        }
        if (recordTable.size() == 11) {
            recordTable.remove(10);
        }
        try (ObjectOutputStream stream =
                     new ObjectOutputStream(new FileOutputStream(".\\MinesweeperUI\\src\\resources\\" + type + ".bin"))) {
            for (RecordNote recordNote : GameField.recordTable.getRecordTable()) {
                stream.writeObject(recordNote);
            }

        } catch (IOException e) {
            System.out.println("Ошибка записи файла таблицы рекордов. " + type + ".bin - " + e.getMessage());
        }
    }

    public boolean isNewRecord(int result) {
        if (recordTable.size() < 10) {
            return true;
        }
        return result < worstRecord;
    }
}
