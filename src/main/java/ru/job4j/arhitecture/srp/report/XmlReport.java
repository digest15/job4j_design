package ru.job4j.arhitecture.srp.report;

import ru.job4j.arhitecture.srp.report.model.Employee;
import ru.job4j.arhitecture.srp.report.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class XmlReport implements Report {
    private final Store store;
    private final Marshaller marshaller;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public XmlReport(Store store) throws JAXBException {
        this.store = store;
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setAdapter(new CalendarAdapter());
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        StringJoiner xml = new StringJoiner(System.lineSeparator());
        try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(employees), writer);
                xml.add(writer.getBuffer().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml.toString();
    }

    @XmlRootElement(name = "employees")
    private static class Employees {
        List<Employee> employees;

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public Employees() {
        }

        @XmlElement(name = "employee")
        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    public static class CalendarAdapter extends XmlAdapter<String, Calendar> {

        public CalendarAdapter() {
        }

        @Override
        public Calendar unmarshal(String s) throws Exception {
            return null;
        }

        @Override
        public String marshal(Calendar calendar) throws Exception {
            return DATE_FORMAT.format(calendar.getTime());
        }
    }
}
