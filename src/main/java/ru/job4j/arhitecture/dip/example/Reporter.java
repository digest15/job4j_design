package ru.job4j.arhitecture.dip.example;

import java.util.List;

/**
 * Example 2
 * Этот класс зависит от конкретной реализации хранилища строк отчетов
 * и возвращает конкретный класс отчета
 * Тут нужно выделить интерфейсы хранилища, отчета, да и самого генератора отчетов
 */
public class Reporter {
    private final DBStore dbStore = new DBStore();

    private final String date;

    public Reporter(String date) {
        this.date = date;
    }

    BalanceReport generate() {
        List<ReportRecord> reportRecords = dbStore.getRecords(reportRecord -> reportRecord.getValue().startsWith(date));
        return new BalanceReport(reportRecords);
    }
}
