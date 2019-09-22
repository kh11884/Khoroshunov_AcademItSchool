package model;

import java.io.Serializable;

public class RecordNote implements Serializable {
    private String name;
    private int recordValue;

    public RecordNote(String name, int recordValue) {
        this.name = name;
        this.recordValue = recordValue;
    }

    public String getName() {
        return name;
    }

    public int getRecordValue() {
        return recordValue;
    }
}
