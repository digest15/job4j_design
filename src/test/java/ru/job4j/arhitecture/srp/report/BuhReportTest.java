package ru.job4j.arhitecture.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.srp.report.currency.Currency;
import ru.job4j.arhitecture.srp.report.currency.CurrencyConverter;
import ru.job4j.arhitecture.srp.report.currency.InMemoryCurrencyConverter;
import ru.job4j.arhitecture.srp.report.formatter.DateTimeParser;
import ru.job4j.arhitecture.srp.report.formatter.ReportDateTimeParser;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class BuhReportTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        store.add(worker);
        Currency fromCurrency = Currency.RUB;
        Currency toCurrency = Currency.USD;
        Report engine = new BuhReport(store, parser, currencyConverter, fromCurrency, toCurrency);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(currencyConverter.convert(
                        fromCurrency,
                        worker.getSalary(),
                        toCurrency))
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}