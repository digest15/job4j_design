package ru.job4j.arhitecture.srp.report;

import ru.job4j.arhitecture.srp.report.currency.Currency;
import ru.job4j.arhitecture.srp.report.currency.CurrencyConverter;
import ru.job4j.arhitecture.srp.report.formatter.DateTimeParser;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class BuhReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    protected final CurrencyConverter currencyConverter;
    protected Currency from;
    protected Currency to;

    public BuhReport(Store store, DateTimeParser<Calendar> dateTimeParser,
                     CurrencyConverter currencyConverter, Currency from, Currency to) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currencyConverter = currencyConverter;
        this.from = from;
        this.to = to;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(currencyConverter.convert(
                                    from,
                                    employee.getSalary(),
                                    to)
                    )
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
