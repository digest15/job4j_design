package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Task {
    private String description;
    private Integer estimation;
    private Person worker;
    private Boolean completed;
    private Person[] observers;

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
