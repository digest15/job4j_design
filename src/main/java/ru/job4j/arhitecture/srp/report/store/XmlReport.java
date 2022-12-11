package ru.job4j.arhitecture.srp.report.store;

import ru.job4j.arhitecture.srp.report.Report;
import ru.job4j.arhitecture.srp.report.model.Employee;

import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class XmlReport implements Report {
    private final Store store;
    private final Marshaller marshaller;

    public XmlReport(Store store, Marshaller marshaller) {
        this.store = store;
        this.marshaller = marshaller;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        StringJoiner xml = new StringJoiner(System.lineSeparator());
        try (StringWriter writer = new StringWriter()) {
            for (Employee em : employees) {
                marshaller.marshal(em, writer);
                xml.add(writer.getBuffer().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml.toString();
    }
}
