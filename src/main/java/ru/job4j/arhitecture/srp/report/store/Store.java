package ru.job4j.arhitecture.srp.report.store;

import ru.job4j.arhitecture.srp.report.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    void add(Employee em);

    List<Employee> findBy(Predicate<Employee> filter);
}
