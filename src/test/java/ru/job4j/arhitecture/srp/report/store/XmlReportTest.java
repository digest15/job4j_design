package ru.job4j.arhitecture.srp.report.store;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.srp.report.Report;
import ru.job4j.arhitecture.srp.report.XmlReport;
import ru.job4j.arhitecture.srp.report.model.Employee;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

class XmlReportTest {
    @Test
    public void whenXmlReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(1987, Calendar.AUGUST, 22, 0, 0, 0);
        date.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        String dateString = formatter.format(date.getTime());
        Employee worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
        Report engine = new XmlReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(System.lineSeparator())
                .append("<employees>")
                .append(System.lineSeparator())
                .append("    <employee>")
                .append(System.lineSeparator())
                .append(String.format("        <name>%s</name>", worker.getName()))
                .append(System.lineSeparator())
                .append(String.format("        <hired>%s</hired>", dateString))
                .append(System.lineSeparator())
                .append(String.format("        <fired>%s</fired>", dateString))
                .append(System.lineSeparator())
                .append(String.format("        <salary>%.1f</salary>", worker.getSalary()))
                .append(System.lineSeparator())
                .append("    </employee>")
                .append(System.lineSeparator())
                .append("</employees>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

}