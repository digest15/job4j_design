package ru.job4j.serialization;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {

    private String description;
    @XmlAttribute
    private Integer estimation;
    private Person worker;
    @XmlAttribute
    private Boolean completed;
    @XmlElementWrapper(name = "observers")
    @XmlElement(name = "person")
    private Person[] observers;

    public Task() {
    }

    public Task(String description, Integer estimation, Person worker, Boolean completed, Person[] observers) {
        this.description = description;
        this.estimation = estimation;
        this.worker = worker;
        this.completed = completed;
        this.observers = observers;
    }

    @Override
    public String toString() {
        return "Task{"
                + "description='" + description + '\''
                + ", estimation=" + estimation
                + ", worker=" + worker
                + ", completed=" + completed
                + ", observers=" + Arrays.toString(observers)
                + '}';
    }
}
