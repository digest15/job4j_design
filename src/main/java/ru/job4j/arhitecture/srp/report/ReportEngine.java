package ru.job4j.arhitecture.srp.report;

import ru.job4j.arhitecture.srp.report.formatter.DateTimeParser;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngine extends AbstractEmloyeeReport {
    public ReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser) {
        super(store, dateTimeParser);
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
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
