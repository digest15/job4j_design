package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.function.Predicate.*;

public class Config {
    private final String path;
    private Map<String, String> values;

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines()
                    .filter(not(String::isEmpty))
                    .filter(not(this::isComment))
                    .filter(this::isCorrectPropertyEntry)
                    .collect(Collectors.toMap(
                            str -> str.substring(0, str.indexOf('=')),
                            str -> str.substring(str.indexOf('=') + 1, str.length())
                    ));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String value(String key) {
        if (values == null) {
            load();
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app.properties"));
    }

    private boolean isCorrectPropertyEntry(String entry) throws IllegalArgumentException {
        if (!entry.matches(".+=.+")) {
            throw new IllegalArgumentException(String.format("%s -> %s", path, entry));
        }
        return true;
    }

    private boolean isComment(String entry) {
        return entry.startsWith("#");
    }
}
