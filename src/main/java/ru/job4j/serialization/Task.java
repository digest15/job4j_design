package ru.job4j.serialization;

import org.json.JSONPropertyIgnore;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    @JSONPropertyIgnore
    public Person getWorker() {
        return worker;
    }

    public void setWorker(Person worker) {
        this.worker = worker;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Person[] getObservers() {
        return observers;
    }

    public void setObservers(Person[] observers) {
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
