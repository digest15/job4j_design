package ru.job4j.io;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("logs.txt"))) {
            result = reader.lines().filter(str -> str.matches(".* 404 \\b")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
