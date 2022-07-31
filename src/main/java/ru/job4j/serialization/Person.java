package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class Person {
    @XmlAttribute
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
}
