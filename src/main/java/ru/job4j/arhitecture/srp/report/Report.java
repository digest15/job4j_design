package ru.job4j.arhitecture.srp.report;

import ru.job4j.arhitecture.srp.report.model.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}