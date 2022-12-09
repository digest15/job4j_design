package ru.job4j.arhitecture.srp.report;

import ru.job4j.arhitecture.srp.report.formatter.DateTimeParser;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HrReport extends AbstractEmloyeeReport {
    public HrReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        super(store, dateTimeParser);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees.sort(Comparator
                .comparing(Employee::getSalary)
                .reversed());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
