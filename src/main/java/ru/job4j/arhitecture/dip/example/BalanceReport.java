package ru.job4j.arhitecture.dip.example;

import java.util.List;

/**
 * Examole 3
 * Этот класс зависит от конкретной реализации печати в консоль
 * Тут нужно выделить интерфейс самого отчета
 * и интерфейс печати отчета
 */
public class BalanceReport {

    private List<ReportRecord> reportRecords;

    public BalanceReport(List<ReportRecord> reportRecords) {
        this.reportRecords = reportRecords;
    }

    public void print() {
        reportRecords.forEach(System.out::println);
    }
}
