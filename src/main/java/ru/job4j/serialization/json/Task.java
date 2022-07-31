package ru.job4j.serialization.json;

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

    public static void main(String[] args) {
        final Task task = new Task("Создать описание класса в формате json",
                30,
                new Person("Вася Пупкин"),
                false,
                new Person[]{new Person("Петр Арсеньтев"), new Person("Есипов Алексей")});

        final Gson gson = new GsonBuilder().create();
        final String jsonStr = gson.toJson(task);

        System.out.println(jsonStr);

        final Task newTask = gson.fromJson(jsonStr, Task.class);
        System.out.println(newTask);
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
