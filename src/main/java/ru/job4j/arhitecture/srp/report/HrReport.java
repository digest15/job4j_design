package ru.job4j.arhitecture.srp.report;

import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HrReport implements Report {
    private final Store store;

    private Comparator<Employee> comparator;

    public HrReport(Store store) {
        this.store = store;
        this.comparator = Comparator
                .comparing(Employee::getSalary)
                .reversed();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees.sort(comparator);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
