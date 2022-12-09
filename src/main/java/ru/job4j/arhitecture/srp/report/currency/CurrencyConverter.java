package ru.job4j.arhitecture.srp.report.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
