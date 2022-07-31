package ru.job4j.serialization.xml;

import ru.job4j.serialization.Person;
import ru.job4j.serialization.Task;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class UseXmlSerialization {
    public static void main(String[] args) throws Exception {
        final Task task = new Task("Создать описание класса в формате json",
                30,
                new Person("Вася Пупкин"),
                false,
                new Person[]{new Person("Петр Арсеньтев"), new Person("Есипов Алексей")});

        JAXBContext context = JAXBContext.newInstance(Task.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(task, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Task newTask = (Task) unmarshaller.unmarshal(reader);
            System.out.println(newTask);
        }
    }
}
