package ru.job4j.arhitecture.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.srp.report.formatter.DateTimeParser;
import ru.job4j.arhitecture.srp.report.formatter.ReportDateTimeParser;
import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ItReportTest {
    @Test
    public void whenItReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        String delimetr = ";";
        Report engine = new ItReport(store, parser, delimetr);
        StringBuilder expect = new StringBuilder()
                .append("Name;Hired;Fired;Salary".replaceAll(";", delimetr))
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(parser.parse(worker.getHired())).append(";")
                .append(parser.parse(worker.getFired())).append(";")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}