package ru.job4j.serialization.jsonjava;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.Person;
import ru.job4j.serialization.Task;

public class UseJsonJava {
    public static void main(String[] args) {
        Person vasia = new Person("Вася Пупкин");

        Task task = new Task("Создать описание класса в формате json",
                30,
                vasia,
                false,
                new Person[]{new Person("Петр Арсеньтев"), new Person("Есипов Алексей")});

        vasia.setCurrentTask(task);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description", task.getDescription());
        jsonObject.put("estimation", task.getEstimation());
        jsonObject.put("worker", task.getWorker());
        jsonObject.put("completed", task.getCompleted());
        jsonObject.put("observers", new JSONArray(task.getObservers()));

        String json = jsonObject.toString();
        System.out.println(json);

        JSONObject newJsonObject = new JSONObject(json);
        System.out.println(newJsonObject);
    }
}
