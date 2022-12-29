package ru.job4j.arhitecture.dip.example;

public class ReportRecord {
    private String value;

    public ReportRecord(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
