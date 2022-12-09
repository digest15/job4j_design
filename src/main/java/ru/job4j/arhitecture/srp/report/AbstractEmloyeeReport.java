package ru.job4j.arhitecture.srp.report;

import ru.job4j.arhitecture.srp.report.Report;
import ru.job4j.arhitecture.srp.report.formatter.DateTimeParser;
import ru.job4j.arhitecture.srp.report.store.Store;

import java.util.Calendar;

public abstract class AbstractEmloyeeReport implements Report {
    protected final Store store;
    protected final DateTimeParser<Calendar> dateTimeParser;

    public AbstractEmloyeeReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }
}
