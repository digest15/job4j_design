package ru.job4j.arhitecture.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;


class JsonReportTest {
    @Test
    public void whenJsonReportGenerated() {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(1987, Calendar.AUGUST, 22, 0, 0, 0);
        Employee worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
        Report engine = new JsonReport(store);
        String expect = """
                [
                  {
                    "name": "Ivan",
                    "hired": {
                      "year": 1987,
                      "month": 7,
                      "dayOfMonth": 22,
                      "hourOfDay": 0,
                      "minute": 0,
                      "second": 0
                    },
                    "fired": {
                      "year": 1987,
                      "month": 7,
                      "dayOfMonth": 22,
                      "hourOfDay": 0,
                      "minute": 0,
                      "second": 0
                    },
                    "salary": 100.0
                  }
                ]""";
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}