package ru.job4j.arhitecture.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.MemStore;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class JsonReportTest {
    @Test
    public void whenJsonReportGenerated() {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(1987, Calendar.AUGUST, 22, 0, 0, 0);
        Employee worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
        Gson gson = new GsonBuilder().create();
        Report engine = new JsonReport(store, gson);
        String expect = gson.toJson(List.of(worker));
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}