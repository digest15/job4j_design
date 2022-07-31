package ru.job4j.serialization;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlAttribute
    private String name;

    private Task currentTask;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Task currentTask) {
        this.name = name;
        this.currentTask = currentTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + '}';
    }
}
