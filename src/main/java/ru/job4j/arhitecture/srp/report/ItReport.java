package ru.job4j.arhitecture.srp.report;

import ru.job4j.arhitecture.srp.report.formatter.DateTimeParser;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ItReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    private final String delimetr;

    public ItReport(Store store, DateTimeParser<Calendar> dateTimeParser, String delimetr) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.delimetr = delimetr;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Hired;Fired;Salary".replaceAll(";", delimetr))
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(delimetr)
                    .append(dateTimeParser.parse(employee.getHired())).append(delimetr)
                    .append(dateTimeParser.parse(employee.getFired())).append(delimetr)
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
