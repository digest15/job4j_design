package ru.job4j.arhitecture.srp.report;

import com.google.gson.Gson;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class JsonReport implements Report {
    private final Store store;
    private final Gson gson;

    public JsonReport(Store store, Gson gson) {
        this.store = store;
        this.gson = gson;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return gson.toJson(employees);
    }
}
