package ru.job4j.arhitecture.dip.example;

/**
 * Example 1
 * Этот класс зависит от конкретных реализаций
 * генератора отчета и конкретного отчета
 */
public class App {
    public static void main(String[] args) {
        Reporter reporter = new Reporter("2022");
        BalanceReport balanceReport = reporter.generate();
        balanceReport.print();
    }
}
