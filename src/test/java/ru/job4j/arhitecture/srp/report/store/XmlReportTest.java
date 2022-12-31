package ru.job4j.arhitecture.srp.report.store;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.srp.report.Report;
import ru.job4j.arhitecture.srp.report.XmlReport;
import ru.job4j.arhitecture.srp.report.model.Employee;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

class XmlReportTest {
    @Test
    public void whenXmlReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(1987, Calendar.AUGUST, 22, 0, 0, 0);
        date.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        Employee worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
        Report engine = new XmlReport(store);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>Ivan</name>
                        <hired>22:08:1987 05:00</hired>
                        <fired>22:08:1987 05:00</fired>
                        <salary>100.0</salary>
                    </employee>
                </employees>
                """;
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }

}