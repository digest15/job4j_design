package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.Person;
import ru.job4j.serialization.Task;

public class UseJsonSerialization {
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
}
