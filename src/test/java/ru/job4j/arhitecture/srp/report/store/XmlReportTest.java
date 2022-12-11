package ru.job4j.arhitecture.srp.report.store;

import org.junit.jupiter.api.Test;
import ru.job4j.arhitecture.srp.report.Report;
import ru.job4j.arhitecture.srp.report.model.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class XmlReportTest {
    @Test
    public void whenXmlReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(1987, Calendar.AUGUST, 22, 0, 0, 0);
        Employee worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Report engine = new XmlReport(store, marshaller);
        String expect = getExpected(marshaller, worker);
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }

    private String getExpected(Marshaller marshaller, Employee employee) throws JAXBException {
        String xml;
        StringWriter writer = new StringWriter();
        marshaller.marshal(employee, writer);
        xml = writer.getBuffer().toString();
        return xml;
    }
}