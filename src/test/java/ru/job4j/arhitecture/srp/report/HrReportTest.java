package ru.job4j.arhitecture.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.srp.report.formatter.DateTimeParser;
import ru.job4j.arhitecture.srp.report.formatter.ReportDateTimeParser;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class HrReportTest {
    @Test
    public void whenHrReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Report engine = new HrReport(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())

                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())

                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}