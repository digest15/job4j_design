package ru.job4j.gc.leak.generators;

import ru.job4j.gc.leak.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate<User> {
    public static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public static final int NEW_USERS_COUNT = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public List<User> generate() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < NEW_USERS_COUNT; i++) {
            users.add(new User(
                    String.format("%s %s %s",
                            surnames.get(random.nextInt(surnames.size())),
                            names.get(random.nextInt(names.size())),
                            patrons.get(random.nextInt(patrons.size()))
                    )
            ));
        }
        return users;
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
