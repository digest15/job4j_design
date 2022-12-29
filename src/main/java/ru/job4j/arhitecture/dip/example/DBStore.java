package ru.job4j.arhitecture.dip.example;

import java.util.List;
import java.util.function.Predicate;

public class DBStore {
    public List<ReportRecord> getRecords(Predicate<ReportRecord> filter) {
        return null;
    }

    public void add(ReportRecord record) {

    }
}
